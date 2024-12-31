# Hogwarts Adventure Game

## Overview
Hogwarts Adventure Game is a text-based RPG where players embark on a magical journey at Hogwarts School of Witchcraft and Wizardry. Players select a character, join a Hogwarts house, and navigate through various challenges while enhancing their skills and preparing for an ultimate test on the fifth day.

---

## How to Play

1. **Launch the Game:**
   - Run the `Main` class to start the game.

2. **Main Menu:**
   - Choose from the following options:
     - `1`: View previous saved games.
     - `2`: Start a new game.

3. **Character Selection:**
   - Choose a character from:
     - Hermione Granger
     - Harry Potter
     - Ron Weasley
   - Each character comes with unique advantages.

4. **House Selection:**
   - "Sorting Hat" will choose a house for you.

5. **Interact with Hagrid:**
   - Optionally receive hints from Hagrid to prepare for the challenges ahead.

6. **Skill Development Days:**
   - Play through 4 days to improve your character's skills in areas like intelligence, defense, and magic.
   - Make strategic decisions to maximize skill gains.

7. **Interim Menu:**
   - After each day, choose from:
     - `1`: Continue the game.
     - `2`: View your character's status.
     - `3`: Save and exit the game.

8. **Final Challenge:**
   - On the 5th day, face the ultimate test to prove your wizardry skills.

---

## Features

- **Character Customization:**
  - Each character has unique traits and advantages.

- **Dynamic Story Progression:**
  - Day-by-day gameplay allows for strategic planning and character development.

- **Save System:**
  - Save your progress.

- **Interactive NPCs:**
  - Receive hints and guidance from Hagrid to prepare for challenges.

---

## Classes and Components

1. **Main Class:**
   - Handles the game flow, including menus, character selection, and gameplay loops.

2. **Karakter Class:**
   - Base class for all playable characters. Contains attributes and methods for skill and house management.

3. **Character Subclasses:**
   - `Hermione`, `Harry`, `Ron`
     - Specialized implementations for each character.

4. **EvSecimi Class:**
   - Manages house selection.

5. **OyunKaydet Class:**
   - Handles saving and loading of game progress.

6. **OyunHikayesi Class:**
   - Manages daily story progression and skill-building tasks.

---

## Controls

- Input numbers (`1`, `2`, `3`, etc.) or characters (`E`, `H`) as prompted.
- Follow on-screen instructions to navigate through the game.

---

## Requirements

- **Java Development Kit (JDK)** version 8 or higher.
- A text-based console or IDE to run the program.

---

## Installation

1. Clone the repository or download the source files.
2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).
3. Run the `Main` class to start the game.

---

## Saving and Loading Games

- **Save Progress:**
  - Choose option `3` in the interim menu to save and exit.

- **Load Progress:**
  - Select option `1` in the main menu to view and load saved games.

---

## Future Enhancements

- Implement a graphical user interface (GUI).
-Voldemort War should be completely edited
-The game will be fully translated into English

