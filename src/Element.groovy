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

      //  println "sorted versions: $sorted"
        //println(sorted[1])
        return sorted[1] //returns the newest version
    }
    static elements = ["test","java","etc"];
    // TODO
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
    //static void deployElement(Map element,Map fournisseur){
       /* def provider = element.get("provider");
        element.remove("provider")
        element.remove("deploymentType")
        element.remove("os")
        println(element)
        def test = element.keySet()
        for(def tests : test){
            if(!fournisseur.get(tests)){
                call(tests,provider)
            }
            else if (element.get("tests") != "null" && !mostRecentVersion([element.get("tests"),fournisseur.get(tests)]) == element.get("tests") ){
                call(tests,provider)
            }
        }*/

    }

    private static call(String element, String fournisseur){
        def  elementTodeploy = element+fournisseur;
        if(elements.contains("test")) {
            switch (elementTodeploy) {
                case "testgoogle":
                    deployMe();
                    break;
                case "javagoogle":
                    deployMe();
                    break;
                case "etc":
                    deployMe();
                    break;
                default:
                    println("erreur")
                    break;
            }
        }
    }

    static void deployScriptLocal(Map scriptPath ) {
        def path = scriptPath.home_path

       // def cmd1 = "sh source /etc/profile.d/gradle.sh | sh -c cd $path | gradle clean build | gradle --parallel runScript"
     //   def cmd1 = "rhythmbox"
        File cmd = new File(path+'/cmd1.sh')
        cmd.write( "gradle clean build \n")
        cmd<< "gradle runScript"
        def exec1 = "chmod +x $path/cmd1.sh".execute()
        exec1.waitFor()
        "$path/cmd1.sh".execute()

    }


    static void deployRunServer(Map serverPath ){
        def path = serverPath.get("home_path")
        //def cmd1 = "sh source /etc/profile.d/gradle.sh | sh -c cd $path  | gradle --parallel runServer"
        File cmd = new File(path+'/cmd2.sh')
        cmd.write( "gradle clean build \n")
        cmd << "gradle runServeur \n"
        def exec = "chmod +x $path/cmd2.sh".execute()
        exec.waitFor()
        "$path/cmd2.sh".execute()

    }

    static void installGradleLocal(){
        println "c'est pas la bonne version lel"
    }
    static void installJavaLocal(){
       println "ptdr frere c'est quoi cette version xD"
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