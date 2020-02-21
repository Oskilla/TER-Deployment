class Noeud {
    private mapNoeud=[fournisseur:null, nomNoeud:null,OS:null ]
    public Noeud(String fournisseur, String nomNoeud, String OS){
        this.mapNoeud.fournisseur = fournisseur
        this.mapNoeud.nomNoeud = nomNoeud
        this.mapNoeud.OS = OS
    }

    public String getFournisseur(){
        return this.mapNoeud.fournisseur;
    }
    public String getNomNoeud(){
        return this.mapNoeud.nomNoeud;
    }
    public String getOS(){
        return this.mapNoeud.OS;
    }
    public void deployNoeud(){
       switch (this.mapNoeud.fournisseur){
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
    void addElementToMap(String key, String elem){
        mapNoeud[key] = elem
    }
    private void deployAllAmazon(){
        //todo
    }
    private void deployAllGoogle(){
        //todo
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
}
