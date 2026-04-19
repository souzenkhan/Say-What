import { createNativeStackNavigator } from '@react-navigation/native-stack';
import HomeScreen from '../screens/HomeScreen';
import BluetoothDeviceScreen from '../screens/BluetoothDeviceScreen';
import AudioControlScreen from '../screens/AudioControlScreen';

const Stack = createNativeStackNavigator();

export default function AppNavigator() {
  return (
    <Stack.Navigator initialRouteName="Home">
      <Stack.Screen name="Home" component={HomeScreen} />
      <Stack.Screen name="Bluetooth" component={BluetoothDeviceScreen} />
      <Stack.Screen name="Audio" component={AudioControlScreen} />
    </Stack.Navigator>
  );
}