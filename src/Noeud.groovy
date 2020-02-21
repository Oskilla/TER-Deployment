class Noeud {
    private nomNoeud
    private javaVersion
    private fournisseur //service provider
    private OS
    public Noeud(String javaVersion, String fournisseur, String nomNoeud, String OS){
        this.javaVersion = javaVersion
        this.fournisseur = fournisseur
        this.nomNoeud = nomNoeud
        this.OS = OS
    }

    public String getJavaVersion(){
        return javaVersion
    }
    public String getFournisseur(){
        return fournisseur
    }
    public String getNomNoeud(){
        return nomNoeud
    }
    public String getOS(){
        return OS
    }
    public void deployAll(){

    }
    public void deplayJava() {

    }
}
