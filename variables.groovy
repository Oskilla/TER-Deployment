import org.apache.groovy.json.internal.JsonFastParser
// Source: stackoverflow
String mostRecentVersion(List versions) {
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
     //sorted[-1]
}
//

def os
def javaV = System.getProperty("java.version")
if(System.getenv("OS").contains("Windows_NT")){
     os = "windows"
}



File fh1 = new File("needs.txt")
text = fh1.getText('UTF-8')
text = text.lines().toArray()
println(text)
println(text[0].toString().contains("${os}"))
//println(Eval.me(text[0]))
text[0].iterator()
text = text[0]
while(text.take(1) != "{"){
     text = text.drop(1);
     println(text)
}
text = text.drop(1)
var1 = "";
while(text.take(1) != ";"){
     var1 += text.take(1);
     text = text.drop(1);
}
if(var1.contains("java")){
     version =""
     var1 = var1.reverse()
     while(var1.take(1) != ":"){
          version += var1.take(1)
          var1 = var1.drop(1)
     }
     version = version.reverse()
     println(version.reverse())
}
println("version: "+ version)
println("javaV"+ javaV)
def versions = [version,javaV];
if(javaV.contains(mostRecentVersion(versions))){
     println("j'ai la version la plus recente, on considere que c'est bon")
}

//text2 = text[0].Substring(text[0].IndexOf('[') + 1);
//println(text2)
/*
File file = new File("config.txt")
file.write "# OS: ${os}\n"
file << "# Java_Version : ${javaV}\n"

println file.text

 */