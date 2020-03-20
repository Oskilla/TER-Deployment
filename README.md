Install Groovy

for linux
1) Download and extract groovy 
2) enter in a terminal : sudo mkdir /usr/share/groovy
3) move the extracted files into that directory : sudo mv groovy-vernumb /usr/share/groovy
4) create a new script : sudo nano /etc/profile.d/groovy.sh
	4a) in nano insert the following lines
		export GROOVY_HOME=/usr/share/groovy/groovy-vernumb
		export PATH=$GROOVY_HOME/bin:$PATH	
	4b) save and exit
5) make the script executable : sudo chmod +x /etc/profile.d/groovy.sh
6) now enter the line : source /etc/profile.d/groovy.sh
7) check that groovy work with the line : groovy -version


How to run :

groovyc Main.groovy src/Element.groovy src/Noeud.groovy
groovy Main.groovy src/Element.groovy src/Noeud.groovy
