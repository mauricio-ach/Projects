// *---------------------------- FUNCIONES ------------------------------------*

// !---------------------------- MISCELLANEOUS

/*const getRandomItem = function (object) {
    let key = Object.keys(object)[Math.floor(Math.random() *
        Object.keys(object).length)];
    return {"key": key, "value": object[key]};
}*/

const removeAllEventListeners = function () {
    // *MENU
    playgame.onclick = null;

    // *TUTORIAL
    skip_tuto.onclick = null;
    next_tuto.onclick = null;

    // *GAME
    exit_game.onclick = null;

    // *SCOREBOARD
    username.onkeyup = null;
    send_usern.onclick = null;
    menu_btn.onclick = null;
    playagain.onclick = null;

    // *SOUND
    correct_sound.onended = null;
    wrong_sound.onended = null;
}

const stopSound = function () {
    correct_sound.pause();
    correct_sound.currentTime = 0;

    wrong_sound.pause();
    wrong_sound.currentTime = 0;
    
    gameover_sound.pause();
    gameover_sound.currentTime = 0;
}

const playWrongSound = function () {
    wrong_sound.play();
}

const playCorrectSound = function () {
    correct_sound.play();
}

const setupMenuEventListeners = function () {
    playgame.onclick = initIntro;
}

const setupTutorialEventListeners = function () {
    skip_tuto.onclick = initGame;
    next_tuto.onclick = tutorialStep1;
}

const setupGameEventListeners = function () {
    exit_game.onclick = () => {
        char_word.innerText = "";
        clearInterval(interval);
        correct_sound.onended = null;
        wrong_sound.onended = null;
        stopSound();
        removeAllEventListeners();
        main_menu();
    }
}

const setupScoreBoardEventListeners = function () {
    username.onkeyup = usernameKeyboardHandler;
    send_usern.onclick = processUserName;
    menu_btn.onclick = () => {
        removeAllEventListeners();
        stopSound();
        main_menu();
    }
    playagain.onclick = () => {
        removeAllEventListeners();
        stopSound();
        initGame();
    }
}

const setupSoundEventListeners = function () {
    correct_sound.onended = processNextWave;
    wrong_sound.onended = () => {
        // ? Se agrega un delay después del sonido
        setTimeout(processNextWave, 1500);
    }
}

const setupEventListeners = function () {
    // *MENU
    setupMenuEventListeners();

    // *TUTORIAL
    setupTutorialEventListeners();
    
    // *GAME
    setupGameEventListeners();
    
    // *SCOREBOARD
    setupScoreBoardEventListeners();
    
    // *SOUND
    setupSoundEventListeners();
}

const loadBackgrounds = function () {
    const images = [];
    for (let i = 0; i < background.length; i++) {
        images[i] = new Image();
        images[i].src = background[i].replace(/(url\(|\))/g, "");
        images[i].onload = () => {console.log("+");}
    }
}

const hideElement = function (element) {
    element.classList.add("hide");
}

const showElement = function (element) {
    element.classList.remove("hide");
}

/**
 * Función que desordena un array dado.
 * @param {Array} array - Array a ser desordenado. 
 */
const shuffleArray = function (array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

/**
 * Función que regresa un elemento random de un arreglo dado.
 * @param {Array<any>} array - Array de elementos
 * @returns {any} Un elemento random del arreglo.
 */
const getRandomItem = function (array) {
    return array[Math.floor(Math.random() * array.length)];
}

/** 
 * Función que vacía una lista (arreglo).
 * @param {Array} list - Lista (arreglo) a vaciar.
 */
const emptyList = function (list) { list.length = 0; }

/**
 * Función que vacía un elemento html. 
 * @param {HTMLElement} container - Elemento a vaciar. 
 */
const emptyContainer = function (container) {
    while (container.firstChild) container.removeChild(container.firstChild);
}

const wrongAnswer = function () {
    character.style.backgroundImage = char_img.wrong;
    char_word.innerHTML = "";
    playWrongSound();
}

const correctAnswer = function () {
    score += 100;
    score_cont.innerHTML = "PUNTUACIÓN:</br>"+score+" pts.";
    character.style.backgroundImage = char_img.correct;
    char_word.innerHTML = "";
    playCorrectSound();
}

// !---------------------------- SCOREBOARD

const setScoreTable = function () {
    const score_names = document.querySelectorAll(".score-name");
    const score_numbers = document.querySelectorAll(".score-number");

    for (let i = 0; i < 10; i++) {
        score_names[i].innerText = scores[i].name;
        score_numbers[i].innerText = scores[i].score;
    }
}

const getScorePosition = function () {
    for (let i = 0; i < scores.length; i++)
        if (score >= scores[i].score) 
            return i;
        
    return 11;
}

const isValidUserName = function () {
    return nickname.match(/[a-zA-Z0-9Ññ]{3}/) != null;
}

const processUserName = function () {
    scores = localStorage.getItem("scores");
    if (!scores) {
        scores = [];
        for (let i = 0; i < 10; i++) scores[i] = {name: "AAA", score: 0};
    }
    else
        scores = JSON.parse(scores);

    let score_pos = getScorePosition();
    if (score_pos <= 10) {
        scores.splice(score_pos, 0, {name: nickname, score: score});
        if (score_pos < 10) scores.pop();

        localStorage.setItem("scores", JSON.stringify(scores));
    }

    username.value = "";
    setScoreTable();
    hideElement(gameover_cont);
    showElement(score_table);
    username.classList.remove("username-success");
}

const usernameKeyboardHandler = function (key) {
    username.value = username.value.toUpperCase();
    nickname = username.value;
    
    
    if (isValidUserName()) {
        username.classList.add("username-success");
        username.classList.remove("username-wrong");
        username_error.classList.add("hidden");
        if (key.code == "Enter") processUserName();
    }
    else {
        username.classList.add("username-wrong");
        username.classList.remove("username-success");
        username_error.classList.remove("hidden");
    }
}

// TODO: DOCUMENTAR ESTO.
const scoreBoard = function () {
    hideElement(game_cont);
    hideElement(hint_cont);
    hideElement(score_cont);
    hideElement(exit_game);
    showElement(scoreboard);
    showElement(gameover_cont);

    setupScoreBoardEventListeners();

    char_word.innerHTML = "";
    character.style.backgroundImage = char_img.score;
    container.style.backgroundImage = background[9];
    gameover_sound.play();
}

// !---------------------------- WORD

/**
 * Función que retorna la palabra actual de acuerdo a la oleada.
 * @param {number} wave - Número de oleada actual.
 * @returns {Word} Palabra actual.
 */
const getCurrentWord = function (wave) {
    const cw = current_level.words[wave];
    shuffleArray(cw.options); // Desordenamos las opciones
    return cw;
}

// !---------------------------- ITEMS

/** 
 * Función que procesa el click de un item.
 * @param {Item} item - Item que fue cliqueado.
 */
const clickItem = function (item) {
    for (const i of items) i.element.onclick = null;
    
    clearInterval(interval);
    if (item.srcElement.innerText == current_word.swedish.toUpperCase())
        correctAnswer();
    else
        wrongAnswer();

    // * Después de que termina el sonido se procesa la siguiente oleada
    // * (processNextWave()).
}

/** @type {() => void} - Función anima el movimiento de los items. */
const moveItems = function () {
    const step = current_level.step_speed; // Desplazamiento en cada frame.
    let counter = 0; // Items que han llegado al limite de pantalla

    interval = setInterval(() => {
        // Movemos cada item verticalmente.
        for (const item of items) {
            item.posy += step; // Aumentamos el desplazamiento.
            item.element.style.top = item.posy + 'px';

            // Aumentamos los items no seleccionados.
            if (!flag_tutorial && item.posy > window.innerHeight &&
                !item.isHidden) {
                item.isHidden = true;
                counter++;
            }

            if (flag_tutorial && item.posy >= (window.innerHeight / 4))
                clearInterval(interval);
        }

        if (counter >= items.length) {
            clearInterval(interval);
            wrongAnswer();
        }
    }, 50);
}

/**
 * Función que genera un nuevo item y lo retorna.
 * @param {number} posx - Posición en x del elemento.
 * @param {string} swe_word - Palabra en sueco.
 * @returns {Item} Item generado. 
 */
const generateItem = function (posx, swe_word) {
    /** @type {Item} */
    let acorn = {
        "element": document.createElement("div"),
        "posx": posx,
        "posy": Math.random() * (-100) - 90,
        "swedish": swe_word,
        "background": getRandomItem(item_imgs),
        "height": 0,
        "width": 0,
        "isHidden": false
    };

    acorn.element.style.left = acorn.posx + "%";
    acorn.element.innerText = acorn.swedish.toUpperCase();
    acorn.element.setAttribute("class", "item");
    acorn.element.style.backgroundImage = acorn.background;

    return acorn;
}

/**
 * Función que genera los items del juego.
 * @param {number} num_items - Número de items a generar.
 * @param {Word} word - Palabra actual.
 * @param {HTMLElement} container - Contenedor al cual se agregarán los items.
 */
const generateItems = function (num_items, word, container) {
    emptyList(items); // Vaciamos la lista de items
    emptyContainer(container); // Vaciamos el contenedor del juego

    /** @type {Item} */
    let acorn;

    /** @type {number} - Posición de la respuesta correcta */
    let answer_pos = Math.floor(Math.random() * (num_items));
    
    /** @type {number} - Posición den x del item */
    let posx;

    /** @type {string} - Palabra en sueco a colocar */
    let swe_word;


    for (let i = 0; i < (num_items + 1); i++) {
        
        posx = (100 / (num_items + 2.5)) * (i + 1);
        
        if (i == answer_pos) // Si se está colocando la respuesta correcta
            swe_word = word.swedish;

        // Si aún no se coloca la respuesta correcta
        else if (answer_pos) swe_word = word.options[i];
        // Si ya se colocó la respuesta correcta
        else swe_word = word.options[i-1];
        
        // Creamos un item, lo posicionamos y le damos estilo.
        acorn = generateItem(posx, swe_word);
        items[items.length] = acorn; // Lo metemos a la lista.
        
        // Manejador de eventos de click en cada item
        if (!flag_tutorial) acorn.element.onclick = clickItem;
        else {
            if (i == answer_pos) acorn.element.onclick = tutorialStep5;
            else acorn.element.onclick = playWrongSound;
        }
        if (i == answer_pos) answer_pos = 0;
    }

    items.forEach((i) => {
        container.appendChild(i.element); // Agregamos el item al html.
        i.width = i.element.clientWidth;
        i.height = i.element.clientHeight;
        i.element.style.top = i.posy + 'px';
    });

    // Seleccionamos un item al azar para que sea el primero en bajar.
    let randomi = getRandomItem(items);
    randomi.posy = -randomi.height;
}

// !---------------------------- WAVE

/** @type {() => void} - Función que procesa la siguiente oleada (si la hay). */
const processNextWave = function () {
    console.log("Procesando wave");

    if (current_wave + 1 < current_level.waves) // Si hay siguiente oleada
        initWave();

    else if (current_level.level_name != "hard") // Si no es el último nivel
        initLevel(); // Inicializamos el nivel
    else {
        scoreBoard();
    }
}

/** @type {() => void} - Función que inicializa la oleada. */
const initWave = function () {
    current_wave++; // Siguiente oleada
    current_word = getCurrentWord(current_wave); // Palabra a adivinar
    
    character.style.backgroundImage = char_img.sign; // Ardilla con letro
    char_word.innerHTML = `Palabra a adivinar:</br></br>\
        ${current_word.spanish.toUpperCase()}`;
    hint.innerHTML = "PISTA:</br>"+current_word.hint;

    setRandomBackground();
    generateItems(current_level.options, current_word, game_cont); // Generamos las bellotas que caen
    moveItems(); // Añadimos movimiento a los items
}

// !---------------------------- LEVEL

/** @type {() => void} - Función que inicializa el nivel. */
const initLevel = function () {
    current_level = __CONFIG__.levels[current_level.level + 1]; // Sig. nivel
    current_wave = -1; // Reseteamos las waves
    shuffleArray(current_level.words); // Desordenamos las palabras a adivinar
    initWave(); // Inicializamos la oleada
}

// !---------------------------- HINT

// * NOTHING

// !---------------------------- SCORE

// * NOTHING

// !---------------------------- GAME

/**
 * Función que establece un fondo random al juego.
 */
const setRandomBackground = function () {
    container.style.backgroundImage = getRandomItem(background);
}

/** @type {() => void} - Función que inicializa el juego. */
const initGame = function () {

    current_level = {
        "level": -1,
        "level_name": "menu",
        "options": 0,
        "waves": 0,
        "words": []
    };

    current_wave = -1;

    score = 0;

    flag_tutorial = false;

    hideElement(menu_cont);
    hideElement(intro_cont);
    hideElement(tuto_cont);
    hideElement(next_tuto);
    hideElement(skip_tuto);
    hideElement(gameover_cont);
    hideElement(scoreboard);
    hideElement(score_table);
    showElement(game_cont);
    showElement(score_cont);
    showElement(hint_cont);
    showElement(exit_game);

    score_cont.innerHTML = "PUNTUACIÓN:</br>0 pts."
    character.classList.remove("intro-character1");
    character.style.backgroundImage = char_img.sign;

    setupSoundEventListeners();
    setupGameEventListeners();
    initLevel(); // Inicializamos el nivel
}

// !---------------------------- TUTORIAL

const tutorialStep7 = function () {
    hideElement(next_tuto);

    skip_tuto.innerText = "Jugar ahora";
    showElement(skip_tuto);

    tuto_help.innerHTML = "<p>Eso es todo.</p><p>¡Ya puedes comenzar a jugar!.\
        </p>";
}

const tutorialStep6 = function () {
    showElement(exit_game);
    exit_game.onclick = () => { /* Hacer nada */ }

    tuto_help.innerHTML = "<p>Por último, el botón de arriba a la izquierda te \
        permite salir del juego y volver a la pantalla de inicio.</p>";

    exit_game.onclick = () => {
        char_word.innerText = "";
        clearInterval(interval);
        main_menu();
    }

    next_tuto.onclick = tutorialStep7;
}

const tutorialStep5 = function () {
    clearInterval(interval);
    
    items[0].element.onclick = null;
    items[1].element.onclick = null;
    items[2].element.onclick = null;
    
    playCorrectSound();

    showElement(score_cont);
    score_cont.innerHTML = "PUNTUACIÓN:</br>100 pts."
    
    tuto_help.innerHTML = "<p>Ganarás 100 puntos por cada respuesta correcta.\
    </p>";
    
    next_tuto.onclick = tutorialStep6;
    showElement(next_tuto);
}

const tutorialStep4 = function () {
    tuto_help.innerHTML = "<p>Selecciona la bellota correcta.</p>";
    hideElement(next_tuto);

    generateItems(2, current_word, tuto_items);

    moveItems();

}

const tutorialStep3 = function () {
    tuto_help.innerHTML = "<p>Comenzarán a caer unas deliciosas bellotas con\
    una palabra en sueco.</p><p>Debes seleccionar alguna de ellas, ¡pero ojo!, \
    trata de seleccionar la bellota con la respuesta correcta.</p>";

    next_tuto.onclick = tutorialStep4;
}

const tutorialStep2 = function () {
    showElement(hint_cont);
    hint.innerHTML = "HINT:</br>"+current_word.hint;
    tuto_help.innerHTML = "<p>Del lado derecho está un letrero con una pista \
    de la respuesta correcta.</p>"

    next_tuto.onclick = tutorialStep3;
}

const tutorialStep1 = function () {
    hideElement(intro_cont);
    hideElement(skip_tuto);
    showElement(tuto_cont);

    emptyContainer(tuto_items);

    flag_tutorial = true;
    character.classList.remove("intro-character1");
    character.style.backgroundImage = char_img.sign;

    current_level = __CONFIG__.levels[0];
    current_wave = 0;
    
    current_word = getCurrentWord(current_wave);
    char_word.innerHTML = `Palabra a adivinar:</br></br>\
        ${current_word.spanish.toUpperCase()}`;
    
    tuto_help.innerHTML = "<p>Al iniciar el juego Mika te mostrará en su \
    letrero la palabra en español que debes traducir.</p>"

    next_tuto.onclick = tutorialStep2;
}

// !---------------------------- INTRO

const initIntro = function () {
    character.classList.remove("menu-character");
    character.classList.add("intro-character1");
    character.style.backgroundImage = char_img.intro;
    hideElement(menu_cont);
    showElement(intro_cont);
    showElement(skip_tuto);
    showElement(next_tuto);
}

// !---------------------------- MENU

/** @type {() => void} - Función que inicializa la pantalla de menu. */
const initMenu = function () {
    hideElement(intro_cont);
    hideElement(tuto_cont);
    hideElement(skip_tuto);
    hideElement(next_tuto);
    hideElement(game_cont);
    hideElement(score_cont);
    hideElement(hint_cont);
    hideElement(exit_game);
    hideElement(gameover_cont);
    hideElement(scoreboard);
    hideElement(score_table);
    showElement(menu_cont);

    character.style.backgroundImage = char_img.menu; // Imagen del personaje.
    character.classList.add("menu-character");
    container.style.backgroundImage = background[7]; // Fondo del juego.

    setupTutorialEventListeners();
}

/** 
 * @type {() => void} - Función que maneja la funcionalidad de la pantalla de
 * menú principal.
 */
const main_menu = function () {
    setupMenuEventListeners();
    initMenu();
}