import java.util.Map;

class Element {
    // Source: stackoverflow
    static String mostRecentVersion(List versions) {
        def sorted = versions.sort(false) { a, b ->

            List verA = a.tokenize('.')
            List verB = b.tokenize('.')

            def commonIndices = Math.min(verA.size(), verB.size())

            for (int i = 0; i < commonIndices; ++i) {
                def numA = verA[i].toInteger()
                def numB = verB[i].toInteger()
                //println "comparing $numA and $numB"

                if (numA != numB) {
                    return numA <=> numB
                }
            }
            // If we got this far then all the common indices are identical, so whichever version is longer must be more recent
            verA.size() <=> verB.size()
        }
        return sorted[1] //returns the newest version
    }

    static void deployElement(Map element, Map VM, String fournisseur){
        def keys = element.keySet()
        for (def k : keys){

            switch (k){
                case "Script" :
                    this.installScript(element, VM);
                    break;
                case "Serv" :
                    this.installServer(element, VM);
                    break;
                default:
                    break;
            }
        }

    }

    static void deployScriptLocal(Map scriptPath ) {
        def path = scriptPath.home_path
        File cmd = new File(path+'/cmd1.sh')
        cmd.write( "cd $path \n")
        cmd <<  "gradle clean build \n"
        cmd << "touch fich1\n"
        cmd<< "gradle runScript\n"
        cmd << "touch fich1"
        def exec1 = "chmod +x $path/cmd1.sh".execute()
        exec1.waitFor()
        "$path/cmd1.sh".execute()
        println "RunScript should start"

    }


    static void deployRunServer(Map serverPath ){
        def path = serverPath.get("home_path")
        println "runServer"
        File cmd = new File(path+'/cmd2.sh')
        cmd.write( "cd $path \n")
        cmd << "gradle clean build \n"
        cmd << "touch fich2.txt \n"
        cmd << "gradle runServeur \n"

        def exec = "chmod +x $path/cmd2.sh".execute()
        exec.waitFor()
        "$path/cmd2.sh".execute()
        println "RunServer should start"

    }

    static void installGradleLocal(){
        println "c'est pas la bonne version de gradle"
    }
    static void installJavaLocal(){
        println "c'est pas la bonne version de java"
    }
    static void installScript(Map element,Map Vm){
        if(!this.mostRecentVersion(["1.8",Vm.Java.version]) == Vm.Java.version){
            this.installJavaLocal()
        }
        if(!this.mostRecentVersion(["6.1.1",Vm.Gradle.version]) == Vm.Gradle.version){
            this.installGradleLocal()
        }

        def cmd = "sh source /etc/profile.d/gradle.sh | gradle runScript"
        cmd.execute()
    }
    static void installServer(Map element,Map Vm){
        if(!this.mostRecentVersion(["1.8",Vm.Java.version]) == Vm.Java.version){
            this.installJavaLocal()
        }
        if(!this.mostRecentVersion(["6.1.1",Vm.Gradle.version]) == Vm.Gradle.version){
            this.installGradleLocal()
        }
        def cmd = "sh source /etc/profile.d/gradle.sh |gradle runServer"
        cmd.execute()
    }
}
