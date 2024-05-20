package com.example.himalayantracker.utlis


import com.example.himalayantracker.utlis.Constants.OPEN_GOOGLE
import com.example.himalayantracker.utlis.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            // Greetings
            message.contains("hi") || message.contains("hey") || message.contains("yo") || message.contains("hello") -> {
                when (random) {
                    0 -> "Hi there!"
                    1 -> "Hey!"
                    else -> "error"
                }
            }

            // Goodbye
            message.contains("bye") || message.contains("goodbye") || message.contains("see you") -> {
                when (random) {
                    0 -> "Goodbye!"
                    1 -> "See you later!"
                    2 -> "Take care!"
                    else -> "error"
                }
            }

            // Expressions of gratitude
            message.contains("thank") || message.contains("thanks") -> {
                when (random) {
                    0 -> "You're welcome!"
                    1 -> "No problem!"
                    2 -> "Anytime!"
                    else -> "error"
                }
            }

            // Expressions of gratitude
            message.contains("great") || message.contains("awesome") || message.contains("fantastic") -> {
                when (random) {
                    0 -> "Glad to hear that!"
                    1 -> "Awesome!"
                    2 -> "Fantastic!"
                    else -> "error"
                }
            }

            message.contains("contact") || message.contains("details")  -> {
                "Ok! You may Contact with Mihir Jain : 8107840539"
            }
            // Expressions of disappointment
            message.contains("bad") || message.contains("not good") || message.contains("disappointed") -> {
                when (random) {
                    0 -> "I'm sorry to hear that."
                    1 -> "That's unfortunate."
                    2 -> "Hope things get better soon."
                    else -> "error"
                }
            }


            // Expressions of excitement
            message.contains("excited") || message.contains("awesome") || message.contains("amazing") -> {
                when (random) {
                    0 -> "That sounds exciting!"
                    1 -> "Wow, that's awesome!"
                    2 -> "Amazing!"
                    else -> "error"
                }
            }

            // Respond to questions about the weather
            message.contains("weather") -> {
                when (random) {
                    0 -> "Looks like a beautiful day outside!"
                    1 -> "It's a bit cloudy today."
                    2 -> "I heard it might rain later."
                    else -> "error"
                }
            }

            // Ask about weekend plans
            message.contains("weekend") -> {
                when (random) {
                    0 -> "Any fun plans for the weekend?"
                    1 -> "Got any exciting weekend activities lined up?"
                    2 -> "Looking forward to the weekend?"
                    else -> "error"
                }
            }

            // Respond to talk about hobbies
            message.contains("hobby") || message.contains("interest") -> {
                when (random) {
                    0 -> "What are your hobbies?"
                    1 -> "I love talking about hobbies!"
                    2 -> "Hobbies are always interesting!"
                    else -> "error"
                }
            }

            // Respond to talk about favorite foods
            message.contains("favorite food") || message.contains("food") -> {
                when (random) {
                    0 -> "Food is the best! What's your favorite?"
                    1 -> "Talking about food always makes me hungry!"
                    2 -> "I have so many favorite foods!"
                    else -> "error"
                }
            }

            // Respond to expressions of confusion
            message.contains("confused") || message.contains("don't understand") -> {
                when (random) {
                    0 -> "It's okay to be confused sometimes."
                    1 -> "Let me know if you need clarification!"
                    2 -> "Confusion is the first step to understanding!"
                    else -> "error"
                }
            }
            // Respond to "whoami"
            message.contains("whoami") -> {
                "I'm your friendly chat assistant! You can ask me anything, and I'll do my best to help you out."
            }

            message.contains("how to use himalayan tracker") -> {
                "You can go through the docs of Himlayan tracker"
            }

            // Respond to "who is the CEO of Himalayan Tracker"
            message.contains("CEO of Himalayan Tracker" ) -> {
                "The CEO of Himalayan Tracker is Mihir."
            }

            // Respond to "name of CEO"
            message.contains("name of CEO") -> {
                "The CEO of Himalayan Tracker is Mihir."
            }

            // Respond to "name of team members"
            message.contains("name of team members") -> {
                "The team members of Himalayan Tracker are Mihir, Anurag, Tushar, and Siddhart."
            }

            // Respond to "who implemented this application"
            message.contains("who implemented this application") -> {
                "This application was implemented by the development team at Himalayan Tracker."
            }

            // Respond to "ky kr re ho"
            message.contains("ky kr re ho") -> {
                "Main thik hoon, shukriya! Tum batao, aap kaise hain?"
            }

            // Respond to "kn h tuh"
            message.contains("kon h tuh") -> {
                "Main ek AI bot hoon jo Himalayan Tracker ke liye viksit kiya gaya hai."
            }

            // Respond to "contact details of the development team"
            message.contains("contact details of the development team") || message.contains("Development Team")  -> {
                "You can contact our development team via email:\n" +
                        "1. Mihir: jainsuresh713@gmail.com\n" +
                        "2. Anurag: anurag@example.com\n" +
                        "3. Tushar: tushar@example.com\n" +
                        "4. Siddhart: siddhart@example.com\n" +
                        "Feel free to reach out to us for any queries or feedback!"
            }



            // Respond to "kya kar rahe ho"
            message.contains("kya kar rahe ho") -> {
                "Main bas aapki seva mein hoon. Aap mujhse kuch bhi pooch sakte hain."
            }

            // Respond to "kaise ho"
            message.contains("kaise ho") -> {
                "Main bilkul theek hoon, shukriya! Aap bataiye, aap kaise hain?"
            }

            // Respond to "kahan ho"
            message.contains("kahan ho") -> {
                "Main yahan hoon, aapke sawalon ka jawab dene ke liye."
            }

            // Respond to "tu kaun hai"
            message.contains("tu kaun hai") -> {
                "Main ek AI assistant hoon, jo aapki madad karne ke liye yahan hoon."
            }

            // Respond to "tum kya ho"
            message.contains("tum kya ho") -> {
                "Main ek AI assistant hoon, aapke sawalon ka jawab dene ke liye."
            }

            // Respond to "himalayan tracker kya hai"
            message.contains("himalayan tracker kya hai") -> {
                "Himalayan Tracker ek veb aur mobile aavedan hai jo yatra aur paryatan se jude sujhav, suchna aur anubhav pradan karta hai."
            }

            // Respond to "app kaise banayi gayi hai"
            message.contains("app kaise banayi gayi hai") -> {
                "Himalayan Tracker ka aavedan Mihir, Anurag, Tushar aur Siddhart dwara viksit kiya gaya hai, jo ek sath mil kar kaam karte hain."
            }

            // Respond to "app ka upayog kaise kare"
            message.contains("app ka upayog kaise kare") -> {
                "Himalayan Tracker ka upayog karna bahut hi aasaan hai. Bas aavedan ko download karein, register karein, aur aap tayyar hain apne yatra ka anand lena ke liye!"
            }

            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}