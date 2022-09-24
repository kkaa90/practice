fun main(args: Array<String>) {
    val a = arrayOf("A 6", "B 12", "C 3")
    val answer = compareDate("2022.05.19", arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C") ,a)
    answer.forEach { println(it) }
}
fun change(date : String) : Long{
    var sum : Long = 0
    val d = date.replace("\"", "").split(".")
    sum+=d[0].toLong()*336
    sum+=d[1].toLong()*28
    sum+=d[2].toLong()
    return sum
}
fun matching(terms : Array<String>) : Map<String, Long>{
    val map = mutableMapOf<String, Long>()
    terms.forEach {
        val temp = it.replace("\"","").split(" ")
        map[temp[0]] = temp[1].toLong()*28
    }
    return map
}
fun compareDate(today: String, privacies: Array<String>, array: Array<String>): IntArray {
    var a = mutableListOf<Int>()
    val t = change(today)
    println(t)
    val map = matching(array)
    privacies.forEachIndexed { index, s ->
        val temp = s.replace("\"", "").split(" ")
        val temp2 = change(temp[0])
        val c=map[temp[1]]
        println(temp2)
        if((temp2+ c!!)<=t){
            a.add(index+1)
        }
    }

    return a.toIntArray()
}