import java.lang.Math.pow

//kullanıcının girdiği input stringinin ayrılıpp listeye dönüştürülmüş hali:
var inputStringList = mutableListOf<String>()
var statesList = mutableListOf<String>()
var inputAlphabetList = mutableListOf<String>()
var outputAlphabetList = mutableListOf<String>()

//bu stringlerdeki boşlukları değiştirmeyin!
var outputTableLine1 :String = "Input String:\t     "
var outputTableLine2 :String = "state:\t\t\t "
var outputTableLine3 :String = "output:\t\t\t     "


fun getInputValuesFromLines(inputLine: String): MutableList<String> {
    val lineParts = inputLine.split('{')

    val valuesCombinedWithComma = lineParts[1].removeSuffix("}")
    //virgülle ayrılmış değerleri ayırarak bir list şeklinde return eder:
    return valuesCombinedWithComma.split(',').toMutableList()
}

fun getValuesFromTableLines(line: String): MutableList<String> {

    val listOfValues = line.split('\t').toMutableList()

    //txt dosyasında birden fazla yan yana tab kullandığım için boş elementler oluşabiliyor. onları kaldırmak için:
    val iterator = listOfValues.iterator()
    while (iterator.hasNext()) {
        val element = iterator.next()
        if (element.isEmpty()) {
            iterator.remove()
        }
    }
    return listOfValues
}

fun inputStringControl(inputStr : String, inputAlfabesi: MutableList<String>): Boolean {
    val inputs = inputStr.split("").toMutableList()
    //println("before: $inputs")
    // en baştaki ve sondaki elementler boş string olduğu için onları kaldır:
    inputs.removeAt(0)
    inputs.removeAt(inputs.lastIndex)

    inputStringList = inputs

    //println("after: $inputs")
    //inputs elementlerinin her biri alfabede bulunuyor mu:
    var control = false
    inputs.forEachIndexed { index, iterInputs ->
        inputAlfabesi.forEach() { iterAlfabe->
            if (iterInputs == iterAlfabe) {
                control = true
            }
        }
        if(!control) return false
        else if (index != inputs.lastIndex) control = false
    }
    return true
}

fun findTransition(){
    //TODO: input stringden başlayarak transition table üstünde ilk satırdan başlayarak nereye gideceğini bul:
    //q 'den sonra gelen sayı ile bir sonraki durum için tablonun hangi satırını kontrol edeceğini bul
    //tabi bu sırada durumu ve output'u yazdırmayı unutma

    for (i in inputStringList.indices) outputTableLine1 += "${inputStringList[i]}        "

    outputTableLine2 += "${statesList[0]} ----> "


    var stateIndex = 0
    var newStateIndex = 0
    var outputIndex = ""
    for (i in inputStringList.indices){

        var alfabeIndex = 0
        inputAlphabetList.forEachIndexed { index, s ->
            if (inputStringList[i] == s){
                alfabeIndex = 2*index+1 //tablodaki tek sayı değerleri
            }
        }


        //indexlerdeki +1 ilk index için sütun isimlerini es geçmek için ikincisi state adını es geçmek için:
        newStateIndex = transitionLinesList[stateIndex+2][alfabeIndex][1].toString().toInt()
        outputTableLine2 += "${statesList[newStateIndex]} "
        if(i != inputStringList.size-1) outputTableLine2 += "----> "
        outputIndex = transitionLinesList[stateIndex+2][alfabeIndex+1]
        outputTableLine3 += "$outputIndex        " //boşluğu silmeyin
        stateIndex = newStateIndex

    }

}



fun main(args: Array<String>) {

    //2 dosyanın içindekilerini okuyup satır listelerine yerleştirir:
    if (!readAllFiles()) return

    statesList = getInputValuesFromLines(inputLinesList[0])
    inputAlphabetList = getInputValuesFromLines(inputLinesList[1])
    outputAlphabetList = getInputValuesFromLines(inputLinesList[2])

    print("giriş stringini giriniz: ")
    val inputString = readln()

    if(!inputStringControl(inputString,inputAlphabetList)) {
        println("!HATA: girdiğiniz string input alfabesi ile uyuşmuyor.")

    }
    println()

    transitionLinesList.forEachIndexed{ index, list ->
        for (i in list.indices) {
            if(index>1) {
                print("${list[i]}\t\t")
                if (i == 0) print("\t")
            }
            else{
                if (index == 0 && i == 0)print("\t\t\t")
                print("${list[i]}\t")
            }
        }
        println()
    }
    println()

    findTransition()

    println(outputTableLine1)
    println(outputTableLine2)
    println(outputTableLine3)

}