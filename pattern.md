## pattern
# Поведенческий. 
# Пораждающий. Строитель (Builder)
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

# Структурный.
