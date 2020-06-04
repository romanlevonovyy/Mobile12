import React, {Component} from 'react';
import { View, Text, FlatList, ScrollView} from 'react-native';
import { Card, ListItem } from 'react-native-elements';
import {data} from './data';




export default function App {

    data = [
      {
        id: 1,
        name: 'Roman Levonovyy',
        text: 'Все було чудово'
      },
      {
        id: 2,
        name: 'Roman Levonovyi',
        text: 'Все було чудово'
      },
    ];
        const renderResponseItem = ({name, text , id }) => {
          return (
            <View key={id}>// width: 100, height: 40px 
              <Text>{name}</Text>
              <Text>{text}</Text>
          </View>
          );
        }
        return(
        <Text>{'Короновірусня'}</Text>// font size - чим побільше margin: 0 auto; padding-top: 20px;
           <View >
            {data && this.data.length !== 0 && this.data.map((row) => renderResponseItem(row))}
           </View>
        );
    }
}

export default About;