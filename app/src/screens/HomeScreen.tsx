import { Text, StyleSheet } from 'react-native';
import ScreenContainer from '../components/ScreenContainer';
import PrimaryButton from '../components/PrimaryButton';
import colors from '../theme/colors';

export default function HomeScreen({ navigation }: any) {
  return (
    <ScreenContainer>
      <Text style={styles.title}>Say What?</Text>
      <Text style={styles.subtitle}>Accessible audio streaming</Text>

      <PrimaryButton
        title="Bluetooth Devices"
        onPress={() => navigation.navigate('Bluetooth')}
      />

      <PrimaryButton
        title="Audio Control"
        onPress={() => navigation.navigate('Audio')}
      />
    </ScreenContainer>
  );
}

const styles = StyleSheet.create({
  title: {
    fontSize: 32,
    fontWeight: 'bold',
    marginBottom: 10,
    textAlign: 'center',
    color: colors.text,
    marginTop: 180,
  },
  subtitle: {
    fontSize: 16,
    marginBottom: 30,
    textAlign: 'center',
    color: colors.text,
  },
});