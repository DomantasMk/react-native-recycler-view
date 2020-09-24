# react-native-recycler-view

### IMPORTANT this module is for Android devices, will not work for IOS

![](./ReadMeAssets/RecyclerGif2.gif)

## Getting started

`$ npm install react-native-recycler-view --save`

### Mostly automatic installation

`$ react-native link react-native-recycler-view`

## Usage
- import the module
- supply it with height
- pass the object array as data prop
- make sure object has fields:
  - title: string
  - poster_url: string
  - _id: string
- define an onClick event for the cards, you may get the id from the nativeInfoObject as shown below
```javascript
import RecyclerView from 'react-native-recycler-view';

CardObjects = [{
  _id:"YourObjectId",
  poster_url: "url to the image you want to display to the card",
  title:"title to be displayed",
}]

<RecyclerView
  style={{height: 300}}
  data={CardObjects}
  onClick={(nativeInfoObj) => console.log(nativeInfoObj.nativeEvent.id)}
/>
```
