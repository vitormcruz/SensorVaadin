# Sensor Vaadin Application

A migration from [SensorReact](https://github.com/vitormcruz/SensorReact) to look at how it would work in a Server Side View framework. 

While it worked fine, the React application runs more smoothly since all the processing happens on the browser. For example, the pressure indicator implements a transform rotation: 
on React, changing the rotation value makes a transformation that moves the pressure pointer, on Vaading, changing this value re-renders the entire component, and so, the pressure pointer
is simply drawn on the new location, but the transformation animation does not happen. Maybe if the communication from server to client were more granular, this could work, but I have not made 
further efforts in that direction.
