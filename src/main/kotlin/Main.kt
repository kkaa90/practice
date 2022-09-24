import java.util.Arrays

fun main(args: Array<String>) {
    val array = Array(2501){""}
    val merge = mutableListOf<String>()
    val commands = arrayOf("UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 1 3 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice"
    , "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian"
    , "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4"
    , "PRINT 1 3", "PRINT 1 4")
    val a = mutableListOf<String>()
    commands.forEach { command(it, array, merge, a) }
    val answer = a.toTypedArray()

}
fun command(string : String, array: Array<String>, merge : MutableList<String>, answer : MutableList<String>){
    val s = string.replace("\"","").split(" ")
    when(s[0]){
        "UPDATE" -> {
            if(s.size==4){
                val n = (s[1].toInt()-1)*50 + s[2].toInt()
                var check = true
                merge.forEach { item ->
                    if(item.contains(n.toString())){
                        item.split(" ").forEach{ array[it.toInt()]=s[3] }
                        check = false
                    }
                }
                if(check) array[n] = s[3]
            }
            else {
                array.forEachIndexed { index, item ->
                    if(item==s[1]){
                        merge.forEach { item2 ->
                            if(item2.contains(index.toString())){
                                item2.split(" ").forEach{ array[it.toInt()]=s[2] }
                            }
                        }
                        array[index]=s[2]
                    }
                }
            }
        }
        "MERGE" -> {
            val n = (s[1].toInt()-1)*50 + s[2].toInt()
            val n2 = (s[3].toInt()-1)*50 + s[4].toInt()
            var check = true
            merge.forEachIndexed { index, item ->
                if(item.contains(n.toString())) {
                    merge[index]+=" $n2"
                    check = false
                }
                if(item.contains(n2.toString())) {

                    merge[index]+=" $n"
                    check = false
                }
            }
            if(check){
                merge.add("$n $n2")
            }
        }
        "UNMERGE" -> {
            val n = (s[1].toInt()-1)*50 + s[2].toInt()
            val temp = array[n]
            var m = 0
            merge.forEachIndexed { index, item ->
                if(item.contains(n.toString())){
                    item.split(" ").forEach{ array[it.toInt()]="" }
                    m = index
                }
            }
            merge.removeAt(m)
            array[n]=temp
        }
        "PRINT" -> {
            val n = (s[1].toInt()-1)*50 + s[2].toInt()
            if(array[n]==""){
                answer.add("EMPTY")
            }
            else {
                answer.add(array[n])
            }
        }
    }
}