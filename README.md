# Tower Defense Game (Java)

## 🛡️ Overview
This is a Java-based **Tower Defense Game** that blends real-time gameplay with a modular, object-oriented design. Players place "B Cell" towers to defend against waves of invading bacteria. Towers automatically fire antibody projectiles, and enemies respawn through a wave generator.

The game provides a clean demonstration of animation, interaction, and game-loop management — all written in plain Java.

---

## 🎮 Features
- Placeable Immune Cell towers via interactive buttons
- Animated antibody projectiles that track and attack enemies
- Enemy (bacteria) spawning with wave regeneration
- Real-time game loop and object updates
- Object-oriented architecture using `Animatable` and `Clickable` interfaces

---

## 🧱 Key Classes

| Class | Description |
|-------|-------------|
| `Main.java` | Entry point of the game; sets up the frame, canvas, and main game loop. |
| `B_Cell.java` | Represents a tower that periodically fires antibodies at nearby enemies. |
| `B_CellButton.java` | UI button that lets the player place B Cell towers. |
| `Antibody.java` | Projectile fired by B Cells that tracks and damages bacteria. |
| `Bacteria.java` | Enemy units that move toward the player's base. |
| `BacteriaGenerator.java` | Spawns waves of bacteria over time. |
| `BacteriaRespawn.java` | Handles respawning logic for defeated bacteria. |
| `Background.java` | Renders the game background and static elements. |
| `Clickable.java` | Interface for any object that responds to mouse input (e.g., buttons). |
| `Animatable.java` | Interface for any object that updates and draws itself on each frame. |

---

## 🚀 How to Run

### Requirements
- Java 8 or higher
- Any IDE that supports Java (e.g., IntelliJ, Eclipse, VS Code)

### Instructions
1. Clone or download this repo.
2. Open the project in your IDE.
3. Compile and run `Main.java`.
4. Click the `B_CellButton` to place towers.
5. Watch as antibodies fire and bacteria respawn over waves.

---

## 💡 Design Notes
- The game loop runs using a timer or animation callback (likely inside `Main.java`).
- Towers and enemies are updated every frame via `Animatable`.
- Clickable elements like the tower button implement the `Clickable` interface.
- The structure makes it easy to extend with new towers, enemies, or UI elements.

---

## 📌 Project Status
This game was created as a class project in object-oriented Java programming and serves as a foundation for more advanced tower defense features (e.g., pathfinding, score systems, level progression).

---

## ✍️ Authors
**Brian Keller and Wyatt Young**
Biomedical Engineering BS/MS | Computer Science Minor  
University of Utah
