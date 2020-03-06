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
    static void deployElement(Map element,Map fournisseur){
        def provider = element.get("provider");
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
        }
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

    static void deployScriptLocal(Map scriptPath ){
        println scriptPath
       def path = scriptPath.home_path
        def cmd1 = "sh -c cd $path"
        println(cmd1)
        cmd1.execute()
        def cmd2 = "gradle clean build"
        cmd2.execute()
        def cmd3 = "gradle runScript"
        cmd3.execute()

    }
    static void deployRunServer(Map serverPath ){
        println "test"
        def path = serverPath.get("home_path")
        def cmd1 = "sh -c cd $path"
        cmd1.execute()
        def cmd2 = "gradle clean build"
        cmd2.execute()
        def cmd3 = "gradle runServer"
        cmd3.execute()
    }
}