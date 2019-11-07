/**
 * @typedef {Object} Level - Objeto que representa un nivel del juego.
 * @property {number} level - Nivel.
 * @property {string} level_name    - Nombre del nivel.
 * @property {number} waves         - Número de oleadas.
 * @property {number} options       - Número de opciones por pregunta.
 * @property {Array<Word>} words    - Arreglo de palabras.       
 */
/** 
 * @typedef {Object} Config - Objeto que contiene la configuración del juego.
 * @property {Array<Level>} levels - Arreglo de niveles.
 */
const __CONFIG__ = {
    "levels": [
        {
            "level": 0,
            "level_name": "easy",
            "waves": 3,
            "options": 2,
            "words": __EASY__,
            "step_speed": 1
        },
        {
            "level": 1,
            "level_name": "medium",
            "waves": 4,
            "options": 4,
            "words": __MEDIUM__,
            "step_speed": 1.3
        },
        {
            "level": 2,
            "level_name": "hard",
            "waves": 5,
            "options": 6,
            "words": __HARD__,
            "step_speed": 1.5
        }
    ]
}

