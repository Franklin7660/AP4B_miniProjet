# 🧩 Interactive Graph Editor
A Java-based interactive graph editor that allows users to create, visualize, edit, and save directed graphs with attributes — perfect for modeling a city's street network and computing shortest paths.

# ✨ Features
Graph Creation and Editing:

Add and remove vertices and edges interactively (mouse and keyboard controls).

Edit vertex and edge attributes (e.g., position, distance, street name).

Visualization:

Display the graph with customizable colors and styles.

Zoom in and out for easier navigation.

Shortest Path Calculation:

Compute shortest paths between nodes.

File Management:

Load and save graphs from files with structured descriptions.

Design Principles:

Built with the Model-View-Controller (MVC) pattern.

UML-based design and analysis methodology.

# 🚀 How to Run
Clone the repository:

bash
Copier
Modifier
git clone https://github.com/yourusername/graph-editor-java.git
cd graph-editor-java
Compile the project:

bash
Copier
Modifier
javac -d bin src/**/*.java
Run the application:

bash
Copier
Modifier
java -cp bin Main
Note:
Java 8 or later is recommended.

# 📂 Project Structure
graphql
Copier
Modifier
├── src/
│   ├── controller/   # Control logic for editing and interacting with the graph
│   ├── model/        # Data structures for graphs, vertices, and edges
│   ├── view/         # GUI components for visualization
│   └── Main.java     # Entry point of the application
├── doc/              # UML diagrams and project documentation
├── README.md         # Project description
# 🎯 Future Improvements
Advanced graph algorithms (e.g., A*, Dijkstra enhancements).

Multi-graph and weighted graph support.

Exporting graphs to image formats (e.g., PNG, SVG).

# 📜 License
This project is for academic use at UTBM and follows standard open-source practices.
