/** 
 * @typedef {Object} Word - Objeto que representa a una palabra.
 * @property {string} spanish   - Palabra en español.
 * @property {string} swedish   - Palabra en sueco
 * @property {string} hint      - Hint de la palabra
 * @property {Array<string>} options    - Opciones incorrectas.
 */
/** @type {Array<Word>} - Arreglo de palabras para la dificultad fácil. */
const __EASY__ = [
    {"spanish": "color",
     "swedish": "färg",
     "hint": "Después de saber la respuesta no te vuelvas tan farg-ol, digo, farol.",
     "options": ["djur","saker","objekt","röd","blå","grå","grön","svart","vit","varsågod"]
    },
    {"spanish": "azul",
     "swedish": "blå",
     "hint": "Te puedo explicar muy a fondo pero sería mucho bla bla bla.",
     "options": ["röd","brun","grå","grön","svart","vit","rosa","gul","orange","lila"]
    },
    {"spanish": "amarillo",
     "swedish": "gul",
     "hint": "Cuando tomas de golpe suena gul-p gul-p gul-p.",
     "options": ["röd","brun","grå","grön","svart","vit","rosa","blå","orange","lila"]
    },
    {"spanish": "rojo",
     "swedish": "röd",
     "hint": "¿Cuál ardilla? ¡La que te pega con la ROD-illa!...no es cierto, somos amigos.",
     "options": ["gul","brun","grå","grön","svart","vit","rosa","blå","orange","lila"]
    },
    {"spanish": "verde",
     "swedish": "grön",
     "hint": "En Suecia le llamamos Grönland a un país, sería divertido si llamara Verdelandia en español.",
     "options": ["gul","brun","grå","röd","svart","vit","rosa","blå","orange","lila"]
    },
    {"spanish": "naranja",
     "swedish": "orange",
     "hint": "¿Qué ora-nge es?... digo hora, mi acento siempre me traiciona.",
     "options": ["gul","brun","grå","röd","svart","vit","rosa","blå","grön","lila"]
    },
    {"spanish": "rosa",
     "swedish": "rosa",
     "hint": "¿Por qué a las rosas les llaman así si son rojas?... bueno, rosa será.",
     "options": ["gul","brun","grå","röd","svart","vit","orange","blå","grön","lila"]
    },
    {"spanish": "café",
     "swedish": "brun",
     "hint": "brrrrun brrrrun brrrrun, me voy en mi moto.",
     "options": ["gul","rosa","grå","röd","svart","vit","orange","blå","grön","lila"]
    },
    {"spanish": "negro",
     "swedish": "svart",
     "hint": "Cada vez que Homero se enoja siempre es vart, digo Bart.",
     "options": ["gul","rosa","grå","röd","brun","vit","orange","blå","grön","lila"]
    },
    {"spanish": "blanco",
     "swedish": "vit",
     "hint": "Este color me encanta, es tan VITal.",
     "options": ["gul","rosa","grå","röd","brun","svart","orange","blå","grön","lila"]
    },
    {"spanish": "morado",
     "swedish": "lila",
     "hint": "Pupila, depila, mamila... ¿qué más rima con gorila?.",
     "options": ["gul","rosa","grå","röd","brun","svart","orange","blå","grön","vit"]
    },
    {"spanish": "gris",
     "swedish": "grå",
     "hint": "¿El femenino de ogro es ogra? gra o gro... no lo sé.",
     "options": ["gul","rosa","lila","röd","brun","svart","orange","blå","grön","vit"]
    },
    {"spanish": "animal",
     "swedish": "djur",
     "hint": "Ya me estoy djur-miendo...zzz... ya me desperté",
     "options": ["haj","kackerlacka","ko","fågel","björn","katt","hund","anka","delfin"]
    },
    {"spanish": "tiburón",
     "swedish": "haj",
     "hint": "Si veo un tiburón gritaría HAAAJ",
     "options": ["djur","kackerlacka","ko","fågel","björn","katt","hund","anka","delfin"]
    },
    {"spanish": "cucaracha",
     "swedish": "kackerlacka",
     "hint": "La kackerlacka la kackerlacka, ya no puede caminar.",
     "options": ["djur","haj","ko","fågel","björn","katt","hund","anka","delfin"]
    },
    {"spanish": "vaca",
     "swedish": "ko",
     "hint": "Y la vaca hace koooo, ¡ah no! es mu.",
     "options": ["djur","kackerlacka","haj","fågel","björn","katt","hund","anka","delfin"]
    },
    {"spanish": "pájaro",
     "swedish": "fågel",
     "hint": "Siempre se me ha hecho curiosa la letra å, esta palabra la tiene.",
     "options": ["djur","kackerlacka","ko","haj","björn","katt","hund","anka","delfin"]
    },
    {"spanish": "oso",
     "swedish": "björn",
     "hint": "Se pronuncia biorn, ¿sabes cuál es?",
     "options": ["djur","kackerlacka","ko","fågel","haj","katt","hund","anka","delfin"]
    },
    {"spanish": "gato",
     "swedish": "katt",
     "hint": "En Suecia comemos un pan llamado Lussekatt, significa gatos de Lucía.",
     "options": ["djur","kackerlacka","ko","fågel","björn","haj","hund","anka","delfin"]
    },
    {"spanish": "perro",
     "swedish": "hund",
     "hint": "Yo tenía un perrita de nombre Hund, ya sé no soy muy creativo.",
     "options": ["djur","kackerlacka","ko","fågel","björn","katt","haj","anka","delfin"]
    },
    {"spanish": "pato",
     "swedish": "anka",
     "hint": "Ya había escuchado de ancas de rana pero ¿anka de pato?.",
     "options": ["djur","kackerlacka","ko","fågel","björn","katt","hund","haj","delfin"]
    },
    {"spanish": "delfín",
     "swedish": "delfin",
     "hint": "¡Vaya que es fácil el sueco!",
     "options": ["djur","kackerlacka","ko","fågel","björn","katt","hund","anka","haj"]
    }
]

/** @type {Array<Word>} - Arreglo de palabras para la dificultad medio. */
const __MEDIUM__ = [
    {"spanish": "número",
     "swedish": "nummer",
     "hint": "Este nivel intermedio es muy fácil, es casi la misma palabra.",
     "options": ["en/ett","två","tre","fyra","fem","sex","sju","åtta","nio","tio"]
    },
    {"spanish": "uno",
     "swedish": "en/ett",
     "hint": "En katt, en björn, en hund, ett piano... uno de cada uno.",
     "options": ["nummer","två","tre","fyra","fem","sex","sju","åtta","nio","tio"]
    },
    {"spanish": "dos",
     "swedish": "två",
     "hint": "Se pronuncia como tuvo pero la u es casi muda.",
     "options": ["en/ett","nummer","tre","fyra","fem","sex","sju","åtta","nio","tio"]
    },
    {"spanish": "tres",
     "swedish": "tre",
     "hint": "Si no pronunciaras la s con este número sería igual que en Sueco, facilito.",
     "options": ["en/ett","två","nummer","fyra","fem","sex","sju","åtta","nio","tio"]
    },
    {"spanish": "cuatro",
     "swedish": "fyra",
     "hint": "Si se te olvide sentirás FURIA.",
     "options": ["en/ett","två","tre","nummer","fem","sex","sju","åtta","nio","tio"]
    },
    {"spanish": "cinco",
     "swedish": "fem",
     "hint": "El número más femenino en sueco",
     "options": ["en/ett","två","tre","fyra","nummer","sex","sju","åtta","nio","tio"]
    },
    {"spanish": "seis",
     "swedish": "sex",
     "hint": "Sex sex sex, mi mamá decía que es un número malo.",
     "options": ["en/ett","två","tre","fyra","fem","nummer","sju","åtta","nio","tio"]
    },
    {"spanish": "siete",
     "swedish": "sju",
     "hint": "Ju ju ju, ¿sabrás cuál es este número?",
     "options": ["en/ett","två","tre","fyra","fem","sex","nummer","åtta","nio","tio"]
    },
    {"spanish": "ocho",
     "swedish": "åtta",
     "hint": "Para recordar todo debes practicar una y otta vez.",
     "options": ["en/ett","två","tre","fyra","fem","sex","sju","nummer","nio","tio"]
    },
    {"spanish": "nueve",
     "swedish": "nio",
     "hint": "Cuando le pedía sus juguetes a mi hermano me decía nio, nunca entendí por qué repetía tanto ese número.",
     "options": ["en/ett","två","tre","fyra","fem","sex","sju","åtta","nummer","tio"]
    },
    {"spanish": "diez",
     "swedish": "tio",
     "hint": "Siempre me emociono cuando faltan tio minutos para el recreo.",
     "options": ["en/ett","två","tre","fyra","fem","sex","sju","åtta","nio","nummer"]
    },
    {"spanish": "comida",
     "swedish": "mat",
     "hint": "Quiero mat-ar el hambre",
     "options": ["mjölk","bröd","vatten","jos","smörgås","äpple","fisk","ost","skinka"]
    },
    {"spanish": "leche",
     "swedish": "mjölk",
     "hint": "Se pronuncia más o menos como mielk, ¿sabrás cuál es?.",
     "options": ["mat","bröd","vatten","jos","smörgås","äpple","fisk","ost","skinka"]
    },
    {"spanish": "pan",
     "swedish": "bröd",
     "hint": "El brö.. digo, el pan de cada día.",
     "options": ["mjölk","mat","vatten","jos","smörgås","äpple","fisk","ost","skinka"]
    },
    {"spanish": "agua",
     "swedish": "vatten",
     "hint": "Para mezclar algo las personas lo baten y lo baten y lo baten.",
     "options": ["mjölk","bröd","mat","jos","smörgås","äpple","fisk","ost","skinka"]
    },
    {"spanish": "jugo",
     "swedish": "jos",
     "hint": "Curioso, tanto en español como en sueco empiezan con la misma letra.",
     "options": ["mjölk","bröd","vatten","mat","smörgås","äpple","fisk","ost","skinka"]
    },
    {"spanish": "sandwich",
     "swedish": "smörgås",
     "hint": "Esta palabras tiene tanto la ö como la å, eso es raro.",
     "options": ["mjölk","bröd","vatten","jos","mat","äpple","fisk","ost","skinka"]
    },
    {"spanish": "manzana",
     "swedish": "äpple",
     "hint": "Esta palabra me recuerda al nombre de una compañía, ¿te acuerdas cuál es?",
     "options": ["mjölk","bröd","vatten","jos","smörgås","mat","fisk","ost","skinka"]
    },
    {"spanish": "pescado",
     "swedish": "fisk",
     "hint": "Uffisk, el pescado crudo huele muy feo.",
     "options": ["mjölk","bröd","vatten","jos","smörgås","äpple","mat","ost","skinka"]
    },
    {"spanish": "queso",
     "swedish": "ost",
     "hint": "Puede que la palabra ostras empiece igual que esta pero el queso con ostras no creo que combine bien.",
     "options": ["mjölk","bröd","vatten","jos","smörgås","äpple","fisk","mat","skinka"]
    },
    {"spanish": "jamón",
     "swedish": "skinka",
     "hint": "Ahí van unas palabras que riman: cinta, brinca, finca...",
     "options": ["mjölk","bröd","vatten","jos","smörgås","äpple","fisk","ost","mat"]
    }
]

/** @type {Array<Word>} - Arreglo de palabras para la dificultad difícil. */
const __HARD__ = [
    {"spanish": "tres osos",
     "swedish": "tre björnar",
     "hint": "¿Ya aprendiste a contar? Eres increíble",
     "options": ["hundar är djur","ett vitt hus","ett rött äpple","fem ankor","ost och skinka","vatten eller mjölk","svarta katter"]
    },
    {"spanish": "los perros son animales",
     "swedish": "hundar är djur",
     "hint": "En sueco hay palabras que son iguales en singular y plural, djur por ejemplo.",
     "options": ["tre björnar","ett vitt hus","ett rött äpple","fem ankor","ost och skinka","vatten eller mjölk","svarta katter"]
    },
    {"spanish": "una casa blanca",
     "swedish": "ett vitt hus",
     "hint": "Cuando el artículo es neutro también se marca en el adjetivo, por el ett entonces blanco sería vitt.",
     "options": ["tre björnar","hundar är djur","ett rött äpple","fem ankor","ost och skinka","vatten eller mjölk","svarta katter"]
    },
    {"spanish": "una manzana roja",
     "swedish": "ett rött äpple",
     "hint": "¿Cuántas manzanas? ¿solo una?",
     "options": ["tre björnar","ett vitt hus","hundar är djur","fem ankor","ost och skinka","vatten eller mjölk","svarta katter"]
    },
    {"spanish": "cinco patos",
     "swedish": "fem ankor",
     "hint": "Me gusta verlos nadar y no tienen ancas, es muy relajante.",
     "options": ["tre björnar","ett vitt hus","ett rött äpple","hundar är djur","ost och skinka","vatten eller mjölk","svarta katter"]
    },
    {"spanish": "queso y jamón",
     "swedish": "ost och skinka",
     "hint": "Un sandwich queda muy rico con...",
     "options": ["tre björnar","ett vitt hus","ett rött äpple","fem ankor","hundar är djur","vatten eller mjölk","svarta katter"]
    },
    {"spanish": "agua o leche",
     "swedish": "vatten eller mjölk",
     "hint": "Hay gente que prefiere una u otra antes de dormir.",
     "options": ["tre björnar","ett vitt hus","ett rött äpple","fem ankor","ost och skinka","hundar är djur","svarta katter"]
    },
    {"spanish": "gatos negros",
     "swedish": "svarta katter",
     "hint": "Dicen que ver uno da mala suerte, ¡imagina ver varios!",
     "options": ["tre björnar","ett vitt hus","ett rött äpple","fem ankor","ost och skinka","vatten eller mjölk","hundar är djur"]
    }
]