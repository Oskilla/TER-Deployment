import groovy.json.JsonSlurper

class Noeud {
    static void deployNoeud(Map element, Map Vm,String provider){
        def keys = element.keySet()
        for(def k : keys) {
            println(k)
            def i
            if (element.get(k) == "null") {
                i = 1
            } else {
                i = element.get(k)
            }
            switch(k){
                case "TestSuite":
                    switch(provider){
                        case "local":
                            deployTestSuiteLocal(element,Vm)
                            break;
                        default:
                            println("provider inconnu");
                            break;
                    }
                    break;
                default:
                    println("Noeud non connus")
                    break;
            }

        }
    }
    static void deployNoeud(String element,String Vm,String provider){

        def jsonSlurper = new JsonSlurper()
        def data = jsonSlurper.parse(new File(Vm))
        println data["Client"]["VM"]
        def VmMap = data["Client"]["VM"]
        switch(element){
            case "TestSuite":
                switch(provider){
                    case "local":
                        deployTestSuiteLocal(VmMap)
                        break;
                    default:
                        println("provider inconnu");
                        break;
                }
                break;
            default:
                println("Noeud non connus")
                break;
        }
    }


    private static void deployTestSuiteLocal(Map Vm){
        //Verifier si on a les bonnes versions de graddle et java, sinon il faut les installer
        println Vm.Gradle.version
        if(!Element.mostRecentVersion(["1.8",Vm.Java.version]) == Vm.Java.version){
            Element.installJavaLocal()
        }
        if(!Element.mostRecentVersion(["6.1.1",Vm.Gradle.version]) == Vm.Gradle.version){
            Element.installGradleLocal()
        }
        Element.deployScriptLocal(Vm.Script);
        Element.deployRunServer(Vm.Test_Server);
        return;
    }
}   