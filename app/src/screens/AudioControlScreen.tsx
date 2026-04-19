import { Text, StyleSheet } from 'react-native';
import ScreenContainer from '../components/ScreenContainer';
import PrimaryButton from '../components/PrimaryButton';

export default function AudioControlScreen() {
  return (
    <ScreenContainer>
      <Text style={styles.title}>Audio Control</Text>

      <Text style={styles.info}>Volume: 75%</Text>
      <Text style={styles.info}>Status: Not Connected</Text>

      <PrimaryButton title="Play" onPress={() => console.log('Play')} />
      <PrimaryButton title="Pause" onPress={() => console.log('Pause')} />
    </ScreenContainer>
  );
}

const styles = StyleSheet.create({
  title: {
    fontSize: 24,
    marginBottom: 20,
    fontWeight: 'bold',
  },
  info: {
    fontSize: 16,
    marginBottom: 10,
  },
});