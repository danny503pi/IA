package com.example.data.local

import com.example.data.model.VocabularyItem

/**
 * Vocabulario inicial precargado para Náhuat Vivo.
 * Incluye palabras cotidianas, saludos, elementos de la naturaleza,
 * animales, familia, números y expresiones comunes en lengua Náhuat (Pipil / Náhuat de El Salvador).
 */
object InitialVocabularyData {
    val items = listOf(
        // Saludos y Cortesía
        VocabularyItem(
            id = 1,
            nahuat = "Yeyek tunal",
            spanish = "Buenos días",
            phonetic = "[yé-yek tú-nal]",
            category = "Saludos y Cortesía",
            exampleNahuat = "Yeyek tunal, nupilawan!",
            exampleSpanish = "¡Buenos días, mis hijos!",
            isFavorite = true
        ),
        VocabularyItem(
            id = 2,
            nahuat = "Yeyek tayua",
            spanish = "Buenas noches",
            phonetic = "[yé-yek ta-yú-a]",
            category = "Saludos y Cortesía",
            exampleNahuat = "Yeyek tayua, shiktali mutonal.",
            exampleSpanish = "Buenas noches, que descanses.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 3,
            nahuat = "Ken tinemi?",
            spanish = "¿Cómo estás?",
            phonetic = "[ken ti-né-mi]",
            category = "Saludos y Cortesía",
            exampleNahuat = "Ken tinemi nujikawan?",
            exampleSpanish = "¿Cómo estás, hermano mío?",
            isFavorite = true
        ),
        VocabularyItem(
            id = 4,
            nahuat = "Ni-yultuk, tlasojkamatik",
            spanish = "Estoy bien, gracias",
            phonetic = "[ni-yúl-tuk, tla-soj-ka-má-tik]",
            category = "Saludos y Cortesía",
            exampleNahuat = "Naja ni-yultuk, tlasojkamatik miak.",
            exampleSpanish = "Yo estoy bien, muchas gracias.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 5,
            nahuat = "Tlasojkamatik miak",
            spanish = "Muchas gracias",
            phonetic = "[tla-soj-ka-má-tik mí-ak]",
            category = "Saludos y Cortesía",
            exampleNahuat = "Tlasojkamatik miak pal mukal.",
            exampleSpanish = "Muchas gracias por tu casa.",
            isFavorite = true
        ),
        VocabularyItem(
            id = 6,
            nahuat = "Ne timutastuk",
            spanish = "Hasta luego / Nos vemos",
            phonetic = "[ne ti-mu-tás-tuk]",
            category = "Saludos y Cortesía",
            exampleNahuat = "Ne timutastuk musta.",
            exampleSpanish = "Hasta luego, nos vemos mañana.",
            isFavorite = false
        ),

        // Naturaleza y Cosmos
        VocabularyItem(
            id = 7,
            nahuat = "Tunal",
            spanish = "Sol / Día",
            phonetic = "[tú-nal]",
            category = "Naturaleza",
            exampleNahuat = "Ne tunal sujsul kalaki tiut.",
            exampleSpanish = "El sol ya se oculta por la tarde.",
            isFavorite = true
        ),
        VocabularyItem(
            id = 8,
            nahuat = "Metzti",
            spanish = "Luna / Mes",
            phonetic = "[méts-ti]",
            category = "Naturaleza",
            exampleNahuat = "Ne metzti pepetaka tik ne tayua.",
            exampleSpanish = "La luna brilla en la noche.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 9,
            nahuat = "Sital",
            spanish = "Estrella",
            phonetic = "[sí-tal]",
            category = "Naturaleza",
            exampleNahuat = "Miak sitaltin nemit ka ilwikak.",
            exampleSpanish = "Hay muchas estrellas en el cielo.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 10,
            nahuat = "At",
            spanish = "Agua",
            phonetic = "[at]",
            category = "Naturaleza",
            exampleNahuat = "Naja nikuni at sesek.",
            exampleSpanish = "Yo bebo agua fresca.",
            isFavorite = true
        ),
        VocabularyItem(
            id = 11,
            nahuat = "Tit",
            spanish = "Fuego",
            phonetic = "[tit]",
            category = "Naturaleza",
            exampleNahuat = "Ne tit shutla tik ne tekuil.",
            exampleSpanish = "El fuego arde en el fogón.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 12,
            nahuat = "Tal",
            spanish = "Tierra / Suelo",
            phonetic = "[tal]",
            category = "Naturaleza",
            exampleNahuat = "Ne tutal tejemet tiktasujtat.",
            exampleSpanish = "Nuestra tierra nosotros la amamos.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 13,
            nahuat = "Ejkat",
            spanish = "Viento / Aire",
            phonetic = "[éj-kat]",
            category = "Naturaleza",
            exampleNahuat = "Ne ejkat kwi ne tzunti.",
            exampleSpanish = "El viento mueve las copas de los árboles.",
            isFavorite = false
        ),

        // Animales
        VocabularyItem(
            id = 14,
            nahuat = "Mistun",
            spanish = "Gato",
            phonetic = "[mís-tun]",
            category = "Animales",
            exampleNahuat = "Ne mistun kochituk pan ne kal.",
            exampleSpanish = "El gato está durmiendo sobre la casa.",
            isFavorite = true
        ),
        VocabularyItem(
            id = 15,
            nahuat = "Pelu / Chichi",
            spanish = "Perro",
            phonetic = "[pé-lu / chí-chi]",
            category = "Animales",
            exampleNahuat = "Ne nupelu sujsul wey wan tatzusi.",
            exampleSpanish = "Mi perro es muy grande y ladra.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 16,
            nahuat = "Tutut",
            spanish = "Pájaro / Ave",
            phonetic = "[tú-tut]",
            category = "Animales",
            exampleNahuat = "Ne tutut takwikat tik ne kwawit.",
            exampleSpanish = "El pájaro canta en el árbol.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 17,
            nahuat = "Michin",
            spanish = "Pescado / Pez",
            phonetic = "[mí-chin]",
            category = "Animales",
            exampleNahuat = "Tik ne apan nemit miak michintin.",
            exampleSpanish = "En el río hay muchos peces.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 18,
            nahuat = "Kwat",
            spanish = "Serpiente / Culebra",
            phonetic = "[kwat]",
            category = "Animales",
            exampleNahuat = "Shikitak ne kwat tik ne sakat!",
            exampleSpanish = "¡Mira la serpiente en la hierba!",
            isFavorite = false
        ),
        VocabularyItem(
            id = 19,
            nahuat = "Tustun",
            spanish = "Conejo",
            phonetic = "[tús-tun]",
            category = "Animales",
            exampleNahuat = "Ne tustun motlaluak kisa tik ne kuyunkal.",
            exampleSpanish = "El conejo sale corriendo de la madriguera.",
            isFavorite = false
        ),

        // Familia y Personas
        VocabularyItem(
            id = 20,
            nahuat = "Tlakat",
            spanish = "Hombre / Persona",
            phonetic = "[tlá-kat]",
            category = "Familia y Personas",
            exampleNahuat = "Ini tlakat sujsul tekiti.",
            exampleSpanish = "Este hombre trabaja mucho.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 21,
            nahuat = "Siwat",
            spanish = "Mujer",
            phonetic = "[sí-wat]",
            category = "Familia y Personas",
            exampleNahuat = "Ne siwat kichiwa tamal.",
            exampleSpanish = "La mujer hace tamales.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 22,
            nahuat = "Piltzin",
            spanish = "Niño / Hijo",
            phonetic = "[píl-tsin]",
            category = "Familia y Personas",
            exampleNahuat = "Ne piltzin mawiltia tik ne tayua.",
            exampleSpanish = "El niño juega al anochecer.",
            isFavorite = true
        ),
        VocabularyItem(
            id = 23,
            nahuat = "Siwapil",
            spanish = "Niña / Hija",
            phonetic = "[si-wá-pil]",
            category = "Familia y Personas",
            exampleNahuat = "Ne siwapil kipia se yeyek tutut.",
            exampleSpanish = "La niña tiene un hermoso pajarito.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 24,
            nahuat = "Noya / Nana",
            spanish = "Mamá / Madre",
            phonetic = "[nó-ya / ná-na]",
            category = "Familia y Personas",
            exampleNahuat = "Naja niktasujta nu-noya.",
            exampleSpanish = "Yo quiero mucho a mi mamá.",
            isFavorite = true
        ),
        VocabularyItem(
            id = 25,
            nahuat = "Tata",
            spanish = "Papá / Padre",
            phonetic = "[tá-ta]",
            category = "Familia y Personas",
            exampleNahuat = "Nu-tata kichiwa kalsun.",
            exampleSpanish = "Mi papá hace pantalones.",
            isFavorite = false
        ),

        // Números
        VocabularyItem(
            id = 26,
            nahuat = "Se",
            spanish = "Uno (1)",
            phonetic = "[se]",
            category = "Números",
            exampleNahuat = "Nikpia se kal.",
            exampleSpanish = "Tengo una casa.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 27,
            nahuat = "Ume",
            spanish = "Dos (2)",
            phonetic = "[ú-me]",
            category = "Números",
            exampleNahuat = "Ume mistuntin mawiltiat.",
            exampleSpanish = "Dos gatos juegan.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 28,
            nahuat = "Yey",
            spanish = "Tres (3)",
            phonetic = "[yey]",
            category = "Números",
            exampleNahuat = "Yey sitaltin pepetakat.",
            exampleSpanish = "Tres estrellas brillan.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 29,
            nahuat = "Nawi",
            spanish = "Cuatro (4)",
            phonetic = "[ná-wi]",
            category = "Números",
            exampleNahuat = "Nawi ejkat nemit ka talpaktuk.",
            exampleSpanish = "Cuatro vientos hay sobre el mundo.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 30,
            nahuat = "Makwil",
            spanish = "Cinco (5)",
            phonetic = "[mák-wil]",
            category = "Números",
            exampleNahuat = "Makwil tapaloltin.",
            exampleSpanish = "Cinco saludos.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 31,
            nahuat = "Majtakti",
            spanish = "Diez (10)",
            phonetic = "[maj-ták-ti]",
            category = "Números",
            exampleNahuat = "Majtakti tunal tik ne techan.",
            exampleSpanish = "Diez días en la comunidad.",
            isFavorite = false
        ),

        // Vida Cotidiana y Expresiones
        VocabularyItem(
            id = 32,
            nahuat = "Kal",
            spanish = "Casa / Hogar",
            phonetic = "[kal]",
            category = "Vida Cotidiana",
            exampleNahuat = "Niaw ka nukal.",
            exampleSpanish = "Voy hacia mi casa.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 33,
            nahuat = "Tamal",
            spanish = "Tamal / Pan de maíz",
            phonetic = "[ta-mál]",
            category = "Vida Cotidiana",
            exampleNahuat = "Ne tamal sesek te welek.",
            exampleSpanish = "El tamal frío no es sabroso.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 34,
            nahuat = "Nikmati",
            spanish = "Lo sé / Entiendo",
            phonetic = "[nik-má-ti]",
            category = "Vida Cotidiana",
            exampleNahuat = "Eje, naja nikmati tajtaketza náhuat.",
            exampleSpanish = "Sí, yo sé hablar náhuat.",
            isFavorite = true
        ),
        VocabularyItem(
            id = 35,
            nahuat = "Te nikmati",
            spanish = "No lo sé",
            phonetic = "[te nik-má-ti]",
            category = "Vida Cotidiana",
            exampleNahuat = "Te nikmati kan nemi ne pelu.",
            exampleSpanish = "No sé dónde está el perro.",
            isFavorite = false
        ),
        VocabularyItem(
            id = 36,
            nahuat = "Shitajtuli!",
            spanish = "¡Habla! / ¡Exprésate!",
            phonetic = "[shi-taj-tú-li]",
            category = "Vida Cotidiana",
            exampleNahuat = "Shitajtuli tik nutechan náhuat!",
            exampleSpanish = "¡Habla en náhuat en mi pueblo!",
            isFavorite = false
        ),
        VocabularyItem(
            id = 37,
            nahuat = "Tlasojtla",
            spanish = "Amor / Querer",
            phonetic = "[tla-sój-tla]",
            category = "Vida Cotidiana",
            exampleNahuat = "Naja nimitztlasojtla miak.",
            exampleSpanish = "Yo te quiero mucho.",
            isFavorite = true
        )
    )

    val categories = listOf(
        "Todas",
        "Saludos y Cortesía",
        "Naturaleza",
        "Animales",
        "Familia y Personas",
        "Números",
        "Vida Cotidiana"
    )
}
