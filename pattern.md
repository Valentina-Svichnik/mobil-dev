# Patterns
## Поведенческий. Команда (Command)
Команда — это поведенческий паттерн проектирования, который превращает запросы в объекты, позволяя передавать их как аргументы при вызове методов, ставить запросы в очередь, логировать их, а также поддерживать отмену операций


```
package com.example.myapplication
interface Command {
    fun execute()
}

class OnCommand(private val ce: ConsumerElectronics) : Command {
    override fun execute() {
        ce.on()
    }
}

class MuteAllCommand(internal var ceList: List<ConsumerElectronics>) : Command {
    override fun execute() {
        for (ce in ceList) {
            ce.mute()
        }
    }
}

interface ConsumerElectronics {
    fun on()
    fun mute()
}

class Television : ConsumerElectronics {

    override fun on() {
        println("Television is on!")
    }

    override fun mute() {
        println("Television is muted!")
    }
}

class SoundSystem : ConsumerElectronics {

    override fun on() {
        println("Sound system is on!")
    }
    override fun mute() {
        println("Sound system is muted!")
    }
}

class Button(var c: Command) {

    fun click() {
        c.execute()
    }
}

class UniversalRemote {

    fun getActiveDevice() : ConsumerElectronics{
        val tv = Television()
        return tv
    }
}

fun main(args: Array<String>) {

    val ce = UniversalRemote.getActiveDevice()
    val onCommand = OnCommand(ce)
    val onButton = Button(onCommand)
    onButton.click()

    val tv = Television()
    val ss = SoundSystem()
    val all = ArrayList<ConsumerElectronics>()
    all.add(tv)
    all.add(ss)
    val muteAll = MuteAllCommand(all)
    val muteAllButton = Button(muteAll)
    muteAllButton.click()
}
```
## Пораждающий. Строитель (Builder)

Строитель — это порождающий паттерн проектирования, который позволяет создавать сложные объекты пошагово. Строитель даёт возможность использовать один и тот же код строительства для получения разных представлений объектов.


``` 
package com.example.myapplication

class Borrower(private val email: String,
               private var firstName: String = "",
               private var lastName: String = "") {
    class ShortTimeBorrowerBuilder(email:String){
        private var borrower:Borrower = Borrower(email = email)

        fun firstName(firstName: String) : ShortTimeBorrowerBuilder {
            this.borrower.firstName = firstName
            return this
        }

        fun build() : Borrower {
            return borrower
        }
    }
}

fun main (args: Array<String>) {
    val borrower = Borrower.ShortTimeBorrowerBuilder("email@email.ru").firstName("Name").build()
}
```

## Структурный. Фасад (Facade)
Фасад — это структурный паттерн проектирования, который предоставляет простой интерфейс к сложной системе классов, библиотеке или фреймворку.

```
enumclassLanguage {
 English,
 Italian,
 French;
}

interface Translator {
 fun translate(text: String, textLanguage: Language)
}

class ItalianTranslator : Translator {
 override fun translate(text: String, textLanguage: Language) {
 println("Translate ($text) from ${textLanguage.name} to Italian")
 }
}

class FrenchTranslator : Translator {
 override fun translate(text: String, textLanguage: Language) {
 println("Translate ($text) from ${textLanguage.name} to French")
 }
}

class EnglishTranslator : Translator {
 override fun translate(text: String, textLanguage: Language) {
 println("Translate ($text) from ${textLanguage.name} to English")
 }
}
```

```
class TranslationManager {
 private val italianTranslator = ItalianTranslator()
 private val frenchTranslator = FrenchTranslator()
 private val englishTranslator = EnglishTranslator()

 fun translate(text: String, translateFrom: Language, translateTo: Language) {
 when (translateTo) {
 Language.Italian -> italianTranslator.translate(text, translateFrom)
 Language.French -> frenchTranslator.translate(text, translateFrom)
 Language.English -> englishTranslator.translate(text, translateFrom)
 }
 }
}
```
```
fun main() {
 val translationManager = TranslationManager()
 translationManager.translate("Some text", Language.English, Language.Italian)
 translationManager.translate("Some text", Language.English, Language.French)
}
```


