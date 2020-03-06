class Noeud {
    /*static mapNoeud=[fournisseur:null, nomNoeud:null,OS:null]
    public Noeud(String fournisseur, String nomNoeud, String OS){
        this.mapNoeud.fournisseur = fournisseur
        this.mapNoeud.nomNoeud = nomNoeud
        this.mapNoeud.OS = OS
    }

    static String getFournisseur(){
        return this.mapNoeud.fournisseur;
    }
    static String getNomNoeud(){
        return this.mapNoeud.nomNoeud;
    }
    static String getOS(){
        return this.mapNoeud.OS;
    }*/
    public static int pomme = 0;
    static void deployNoeud(Map element, Map Vm,String provider){
        //def provider = element.get("provider")
        def keys = element.keySet()
        for(def k : keys) {
           // println(k)
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
                            println("si t'arrive là c'est qu'il y a eu un problème");
                            break;
                    }
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
                            println("si t'arrive là c'est qu'il y a eu un problème");
                            break;
                    }
                    break;
                default:
                    break;
            }

        }
    }
    /*
    void addElementToMap(String key, String elem){
        mapNoeud[key] = elem
    }*/
    private static void deployAllAmazon(Map element, Map Vm){
        File fh1 = new File("TEST.txt")
        fh1.append("tchoin!!! \n")
        return;
    }
    private static void deployAllGoogle(Map element, Map Vm){
        println()
        File fh1 = new File("TEST.txt")
        fh1.append("yay!!! \n")
        return ;
    }
    public void deployJava(){
        //todo
    }
    private void deployJavaAmazon(){
        //todo
    }
    private void deploymentJavaGoogle(){
        //todo
    }
    private static deployHelloWorldAmazon(Map Vm){
        //Pour chaque element, verifier ou non son appartenance à la vm
       // println(element);

        //File fh1 = new File("TEST.txt")
        //fh1.append("tchoin!!! \n")
        return ;
    }
   private static void deployTestSuiteLocal(Map element,Map Vm){
       //Verifier si on a les bonnes versions de graddle et java, sinon il faut les installer
       println Vm.Gradle.version
       if(!Element.mostRecentVersion(["1.8",Vm.Java.version]) == Vm.Java.version){
           Element.installJavaLocal()
       }
       if(!Element.mostRecentVersion(["6.1.1",Vm.Gradle.version]) == Vm.Gradle.version){
           Element.installGradleLocal()
       }
        Element.deployScriptLocal(element.Script);
        Element.deployRunServer(element.Test_Server);
       return;
    }
}
