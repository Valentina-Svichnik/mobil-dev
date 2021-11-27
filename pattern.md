# Patterns
## Поведенческий. 
## Пораждающий. Строитель (Builder)

Строитель — это порождающий паттерн проектирования, который позволяет создавать сложные объекты пошагово. Строитель даёт возможность использовать один и тот же код строительства для получения разных представлений объектов.
![image](https://user-images.githubusercontent.com/56071862/143677772-784d7b5f-78ba-46ed-9b6b-fe8a2c527a45.png)


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

## Структурный.
