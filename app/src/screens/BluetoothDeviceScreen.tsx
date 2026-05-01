import { Text, StyleSheet } from 'react-native';
import ScreenContainer from '../components/ScreenContainer';
import PrimaryButton from '../components/PrimaryButton';

export default function BluetoothDeviceScreen() {
  return (
    <ScreenContainer>
      <Text style={styles.title}>Bluetooth Devices</Text>

      <PrimaryButton title="AirPods Pro" />
      <PrimaryButton title="Hearing Aid Left" />
      <PrimaryButton title="Hearing Aid Right" />
    </ScreenContainer>
  );
}

const styles = StyleSheet.create({
  title: {
    fontSize: 24,
    marginBottom: 20,
    fontWeight: 'bold',
  },
});