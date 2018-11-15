package society { 
package professional { 
class Executive { 
private[professional] var workDetails = null 
private[society] var friends = null 
private[this] var secrets = null //It is accessible for this object not even in methods
def help(another : Executive) { 
println(another.workDetails) 
//println(another.secrets) //ERROR 
} } } } 
