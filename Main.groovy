
import groovy.json.JsonSlurper

class Main{
    private static Map parser(String line){
        def VMmap = [:]
        while(line.length() > 0) {
            def key = ""
            def value = ""
            boolean version = false
            while (line.take(1) != "," && line.length() > 0 ) {
                if (line.take(1) == ":" && line.length() > 0) {
                    version = true;
                    line = line.drop(1)
                } else {
                    if (version) {
                        value += line.take(1);
                    } else {
                        key += line.take(1);
                    }
                    line = line.drop(1);
                }
            }
            line = line.drop(1);
            if(value == ""){
                value = "null"
            }
            VMmap[key] = value
        }
        return VMmap
    }
   /* private static void deploy(Map vm,Map needs,String provider){
        switch(needs["deploymentType"]){
            case "nodes":
                Noeud.deployNode(vm,needs)
                break;
            case "elements":
                Element.deployElement(vm,needs)
                break;
            default:
                Element.deployElement(vm,needs)
                break;
        }
    }
*/

    def static Node = {nodeMap,provider,VM -> Noeud.deployNoeud(nodeMap,VM, provider )}
   // def static Elem = {elemToDeploy, provider, VM -> Element.deployElement(elemToDeploy, provider, VM)}
    def static deploy(action){
        [the:{node ->
            [with:{provider->
                [on:{vmname -> action(node,provider,vmname)}]
            }]
        }]
    }
    private static NeedsText,SPvms;
    private spvmMAP
    static void main(String[] args){
        def jsonSlurper = new JsonSlurper()
        def data = jsonSlurper.parse(new File("needs3.json"))
        //def VmMap = data["deployement_info"]["provider"];
        for(def k in data.keySet()){
            def NodeMap = data.get(k).Nodes
            def elemToDeploy = data.get(k).element
            if (NodeMap != null) {
                Noeud.deployNoeud(NodeMap, data.get(k).VM, data.get(k).provider)
            }
            if(elemToDeploy!=null) {
                Element.deployElement(elemToDeploy, data.get(k).VM, data.get(k).provider)
            }
        }


        deploy Node the "application" with "Google" on "VM1"
     //   deploy Elem the "java" with "Amazon" on "VM1"

    }
}