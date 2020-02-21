class Noeud {
    private String nomNoeud;
    private String fournisseur; //service provider
    private String OS;
    public Noeud(String fournisseur, String nomNoeud, String OS){
        this.fournisseur = fournisseur;
        this.nomNoeud = nomNoeud;
        this.OS = OS;
    }

    public String getFournisseur(){
        return fournisseur;
    }
    public String getNomNoeud(){
        return nomNoeud;
    }
    public String getOS(){
        return OS;
    }
    public void deployAll(){
       switch (fournisseur){
            case "Amazon":
                deployAllAmazon();
                break;
            case "Google":
                deployAllGoogle();
                break;
            default:
                println("si t'arrive là c'est qu'il y a eu un problème");
                break;
        }
    }
    private void deployAllAmazon(){
        println("Amazon");
    }
    private void deployAllGoogle(){
        println("Google");
    }
    public void deployJava(){

    }
    private void deployJavaAmazon(){

    }
    private void deploymentJavaGoogle(){

    }
}
