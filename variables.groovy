class Main{
    // Source: stackoverflow
    private String mostRecentVersion(List versions) {
        def sorted = versions.sort(false) { a, b ->

            List verA = a.tokenize('.')
            List verB = b.tokenize('.')

            def commonIndices = Math.min(verA.size(), verB.size())

            for (int i = 0; i < commonIndices; ++i) {
                def numA = verA[i].toInteger()
                def numB = verB[i].toInteger()
                println "comparing $numA and $numB"

                if (numA != numB) {
                    return numA <=> numB
                }
            }
            // If we got this far then all the common indices are identical, so whichever version is longer must be more recent
            verA.size() <=> verB.size()
        }

        println "sorted versions: $sorted"
        println(sorted[1])
        return sorted[1] //returns the newest version
    }
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
    private static void deploy(Map vm,Map needs){

    }

    private static NeedsText,SPvms;
    private spvmMAP
    static void main(String[] args){
        //files init
            //Nodes or coponents to deploy
        File fh1 = new File("C:\\Users\\Nico\\Desktop\\risk\\groovyTuto\\src\\needs.txt")
        NeedsText = fh1.readLines()
        def NeedsMap = [:]
            // VM config
        File fh2= new File("C:\\Users\\Nico\\Desktop\\risk\\groovyTuto\\src\\SPvm.txt")
        SPvms = fh2.readLines('UTF-8')
        def VmMap = [:]
        //Parsing files
        VmMap = parser(SPvms[0])
        NeedsMap = parser(NeedsText[0])
        //deploy
        deploy(VmMap,NeedsMap)
    }
}