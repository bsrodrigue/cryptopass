package bf.rodo.birthdayapp

val charsetMap: Map<String, List<Char>> = mapOf(
    "uppercase" to ('A'..'Z').toList(),
    "lowercase" to ('a'..'z').toList(),
    "digits" to ('0'..'9').toList(),
    "special" to listOf('!', '@', '#', '$', '%', '^', '&', '*')
)

fun getCharset(charsetNames: List<String>): List<Char> {
    val charList = mutableListOf<Char>()

    for (name in charsetNames) {
        charsetMap[name]?.let {
            charList.addAll(it)
        }
    }

    return charList
}

fun genPassword(length: Int, charset: List<Char>): String {
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}