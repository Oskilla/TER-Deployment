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
        if (fournisseur == "Amazon"){
            deployAllAmazon();
        }else if (fournisseur == "Google"){
            deployAllGoogle();
        }
        else{
            println("il y a eu un probleme");
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
