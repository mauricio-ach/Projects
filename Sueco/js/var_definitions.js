// *----------------------------- ELEMENTOS -----------------------------------*
/** @type {HTMLDivElement} - Contenedor del juego. */
const container = document.getElementById("container");

/** @type {HTMLDivElement} - Contenedor del menu (Pantalla inicial). */
const menu_cont = document.getElementById("menu");

/** @type {HTMLDivElement} - Contenedor de la introducción */
const intro_cont = document.getElementById("intro");

/** @type {HTMLDivElement} - Contenedor del tutorial */
const tuto_cont = document.getElementById("tutorial");

/** @type {HTMLDivElement} - Contenedor del tutorial */
const tuto_items = document.getElementById("tutorial-items");

/** @type {HTMLDivElement} - Contenedor de la ayuda del tutorial */
const tuto_help = document.getElementById("help-tutorial");

/** @type {HTMLDivElement} - Contenedor de los items que caen. */
const game_cont = document.getElementById("game");

/** @type {HTMLDivElement} - Contenedor del score. */
const score_cont = document.getElementById("score");

/** @type {HTMLDivElement} - Elemento correspondiente al personaje. */
const character = document.getElementById("character");

/** @type {HTMLDivElement} - Elemento correspondiente a la palabra a adivinar */
const char_word = document.getElementById("word");

/** @type {HTMLDivElement} - Elemento contenedor del hint. */
const hint_cont = document.getElementById("billboard");

/** @type {HTMLDivElement} - Elemento contenedor del texto del hint. */
const hint = document.getElementById("hint");

/** @type {HTMLDivElement} - Contenedor del scoreboard. */
const scoreboard = document.getElementById("scoreboard");

/** @type {HTMLInputElement} - Campo de texto del nombre de usuario. */
const username = document.getElementById("username");

/** @type {HTMLDivElement} - Texto de ayuda que indica error en el nick */
const username_error = document.getElementById("username-error");

/** @type {HTMLButtonElement} - Botón para iniciar el juego (JUGAR). */
const playgame = document.getElementById("playgame");

/** @type {HTMLButtonElement} - Botón para iniciar el juego (JUGAR). */
const playagain = document.getElementById("play-again");

/** @type {HTMLButtonElement} - Botón para saltar la introducción. */
const skip_tuto = document.getElementById("skip");

/** @type {HTMLButtonElement} - Botón para iniciar el tutorial. */
const next_tuto = document.getElementById("next-tutorial");

/** @type {HTMLButtonElement} - Botón para enviar username. */
const send_usern = document.getElementById("send-username");

/** @type {HTMLButtonElement} - Botón para regresar al menú. */
const menu_btn = document.getElementById("main-menu");

const exit_game = document.getElementById("exit-game");

// TODO
const gameover_cont = document.getElementById("gameover-container");

// TODO
const score_table = document.getElementById("score-board");

// TODO
const correct_sound = document.getElementById("correct-sound");

// TODO
const wrong_sound = document.getElementById("wrong-sound");

// TODO
const gameover_sound = document.getElementById("gameover-sound");
// *------------------------------ IMÁGENES -----------------------------------*
/** @type {Array<string>} - Imagen de fondo. */
let background = ["url(./assets/backgrounds/background.jpg)",
    "url(./assets/backgrounds/background_1.jpg)",
    "url(./assets/backgrounds/background_2.jpg)",
    "url(./assets/backgrounds/background_3.jpg)",
    "url(./assets/backgrounds/background_4.jpg)",
    "url(./assets/backgrounds/background_5.jpg)",
    "url(./assets/backgrounds/background_6.png)",
    "url(./assets/backgrounds/background_7.png)",
    "url(./assets/backgrounds/background_8.jpg)",
    "url(./assets/backgrounds/background_winter.jpg)"];

/** 
 * @typedef {Object} - Imágenes del personaje.
 * @property {string} sign - Imagen de la ardilla con letrero.
 * @property {string} menu - Imagen de la ardilla del menu de inicio.
 * @property {string} wrong - Imagen de la ardilla para respuesta incorrecta.
 * @property {string} correct - Imagen de la ardilla para respuesta correcta.
 * @property {string} score - Image de la ardilla para la pantalla de score.
 */
const char_img = {
    "sign": "url(./assets/characters/ardilla-madera.png)",
    "menu": "url(./assets/characters/ardilla-corriendo.png)",
    "intro": "url(./assets/characters/ardilla-volteando.png)",
    "wrong": "url(./assets/characters/ardilla-deprimida.png)",
    "correct": "url(./assets/characters/ardilla-con-bellota.png)",
    "score": "url(./assets/characters/ardilla-con-cafe.png)"};

/** @type {Array<string>} - Imágenes de los items */
const item_imgs = ["url(./assets/items/bellota-1.png)",
    "url(./assets/items/bellota-2.png)", "url(./assets/items/bellota-3.png)"];

// *------------------------- OBJETOS Y VARIABLES -----------------------------*

/** 
 * @typedef {Object} Item - Objeto que representa a una bellota.
 * @property {HTMLDivElement} element   - Elemento HTML asociado.
 * @property {number} posx  - Posición en x del elemento.
 * @property {number} posy  - Posición en y del elemento.
 * @property {string} swedish       - Palabra en sueco.
 * @property {string} background    - Fondo del elemento.
 * @property {number} height        - Altura.
 * @property {number} width         - Ancho.
 */
 /** @type {Array<Item>} - Arreglo de elementos que caen. */
let items = [];

/** @type {Word} - Objeto con la palabra actual a adivinar. */
let current_word = null;

/** 
 * @type {{width: number, height: number}} - Objeto con las dimensiones de la 
 * ventana. */
const _window_ = {
    width: Math.max(document.documentElement.clientWidth,
        window.innerWidth || 0),
    height: Math.max(document.documentElement.clientHeight,
        window.innerHeight || 0)
};

/** @type {number} - Score del jugador actual. */
let score = 0;

/** @type {number} - ID del intervalo que anima el juego. */
let interval;

/** @type {Level} - Nivel actual del juego. */
let current_level = {
    "level": -1,
    "level_name": "menu",
    "options": 0,
    "waves": 0,
    "words": []
};

/** @type {number} - Oleada actual. */
let current_wave = -1;

/** @type {string} - Nombre de usuario. */
let nickname;

/** @type {Array<{name: string, score: number}>} - Arreglo de scores. */
let scores = [];

let flag_tutorial = false;