# TomentMapEditor
A Map Editor for the engine TomentRaycaster

![1](https://user-images.githubusercontent.com/20478938/236866846-8e9d70f5-72d8-4d8b-bf1e-db9b71441735.png)

TomentMapEditor is a map-editor for the [TomentRaycaster](https://github.com/silvematt/TomentRaycaster) engine, written in Java as a proposed university assignment for an exam.

It is able to create, open, edit and export <b>.tmap</b> files with a maximum size of 24x24 (native size of TomentRaycaster maps) while providing an easy to use interface powered by Swing.
 
<h2>Elements:</h2>
- <b>Viewport</b> represents the tilemap as clickable buttons that display the most meaningful element in that tile (walls, sprites, AI).<br>
- <b>Commands</b> allows to travel floors and perform undo operations.<br>
- <b>Palette</b> lists all the placeable elements inside the maps and allows for selection (right click in the map to write on a tile).<br>
- <b>Viewer:</b> displays useful information on the selected tile.<br>
- <b>Selected Info:</b> allows to edit tile properties in base of the current edit mode.<br>
- <b>Toolbar:</b> useful info and "Run In Game" functionality to quickly test maps.<br>

