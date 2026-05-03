import { NavigationContainer } from '@react-navigation/native';
import AppNavigator from './src/navigation/AppNavigator';
import { ConnectionProvider } from './src/context/ConnectionContext';


export default function App() {
  return (
  <ConnectionProvider>
    <NavigationContainer>
      <AppNavigator />
    </NavigationContainer>
  </ConnectionProvider>
  );
}
