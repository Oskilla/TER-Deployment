class Main{
   def static Node = {nodeMap,provider,VM -> Noeud.deployNoeud(nodeMap,VM, provider )}
   def static Elem = {elemToDeploy, provider, VM -> Element.deployElement(elemToDeploy, provider, VM)}
    def static deploy(action){
        [the:{node ->
            [with:{provider->
                [on:{vmname -> action(node,provider,vmname)}]
            }]
        }]
    }

    static void main(String[] args){
        if(args.size() != 10){
            prinln("Error wrong argument size")
        }else {
            if(args[3]=="Node"){
                deploy Node the args[5] with args[7] on args[9]
            }else if (args[3]=="Elem"){
                deploy Elem the args[5] with args[7] on args[9]
            }else {
                println("Type not recognized")
            }
        }
    }
}