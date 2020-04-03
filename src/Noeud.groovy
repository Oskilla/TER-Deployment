import groovy.json.JsonSlurper

class Noeud {
    static void deployNoeud(Map element, Map Vm,String provider){
        //def provider = element.get("provider")
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
                case "HelloWorld":
                    switch (provider){
                        case "Amazon":
                            for(def j=0; j < i; j++) {
                                deployHelloWorldAmazon(element,Vm)
                            }
                            break;
                        case "Google":
                            for(def j=0; j < i; j++) {
                                deployAllGoogle(element,Vm);
                                // deployAllGoogle();
                            }
                            break;
                        default:
                            println("si t'arrive là c'est qu'il y a eu un problème");
                            break;
                    }
                    break;
                case "TestSuite":
                    switch(provider){
                        case "local":
                            deployTestSuiteLocal(element,Vm)
                            break;
                        default:
                            println("si t'arrive là c'est qu'il y a eu un problème1");
                            break;
                    }
                    break;
                case "HelloWorld2":
                    switch (provider){
                        case "Amazon":
                            for(def j=0; j < i; j++) {
                                deployHelloWorld2Amazon();
                            }
                            break;
                        case "Google":
                            for(def j=0; j < i; j++) {
                                deployAllGoogle();
                                //  deployHelloWorld2Google();
                            }
                            break;
                        default:
                            println("si t'arrive là c'est qu'il y a eu un problème2"+k);
                            break;
                    }
                    break;
                default:
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
                        println "yep"
                        break;
                    default:
                        println("si t'arrive là c'est qu'il y a eu un problème1");
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