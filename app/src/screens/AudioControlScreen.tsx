import { Text, StyleSheet } from "react-native";
import ScreenContainer from "../components/ScreenContainer";
import PrimaryButton from "../components/PrimaryButton";
import { Audio } from "expo-av";
import { useRef, useEffect } from "react";
import { useConnection } from '../context/ConnectionContext';



export default function AudioControlScreen() {
  const soundRef = useRef<Audio.Sound | null>(null);
  const { connectionState, setConnectionState } = useConnection();

  
  const handleConnect = () => {
  setConnectionState("connecting");

  setTimeout(() => {
    const didFail = Math.random() < 0.3;

    if (didFail) {
      setConnectionState("error");
    } else {
      setConnectionState("live");
    }
  }, 1500);
};


  const handleDisconnect = () => {
    setConnectionState("idle");
  };

  const handleGoLive = () => {
    setConnectionState("live");
  };

  const handleError = () => {
    setConnectionState("error");
  };


  // 🔥 PLAY FUNCTION
  const handlePlay = async () => {
    try {
      console.log("Trying to play...");

      await Audio.setAudioModeAsync({
        playsInSilentModeIOS: true,
      });

      // If sound already exists → resume it
      if (soundRef.current) {
        await soundRef.current.playAsync();
        console.log("Resuming audio");
        return;
      }

      // Otherwise create new sound
      const { sound } = await Audio.Sound.createAsync(
        { uri: "http://192.168.12.163:3000/audio" },
        { shouldPlay: true, volume: 1.0 },
      );

      soundRef.current = sound;

      console.log("Playing new audio");
    } catch (e) {
      console.log("Play error:", e);
    }
  };

  // 🔥 PAUSE FUNCTION
  const handlePause = async () => {
    try {
      if (soundRef.current) {
        await soundRef.current.pauseAsync();
        console.log("Paused audio");
      }
    } catch (e) {
      console.log("Pause error:", e);
    }
  };

  // 🔥 CLEANUP (important)
  useEffect(() => {
    return () => {
      if (soundRef.current) {
        soundRef.current.unloadAsync();
      }
    };
  }, []);

  return (
    <ScreenContainer>
      <Text style={styles.title}>Audio Control</Text>

      <Text style={styles.info}>Volume: 100%</Text>
      <Text style={styles.info}>Status: {connectionState}</Text>

      <PrimaryButton title="Connect" onPress={handleConnect} />
      <PrimaryButton title="Disconnect" onPress={handleDisconnect} />
      {/* <PrimaryButton title="Set Live" onPress={handleGoLive} />
      <PrimaryButton title="Set Error" onPress={handleError} /> */}

      <PrimaryButton title="Play" onPress={handlePlay} />
      <PrimaryButton title="Pause" onPress={handlePause} />
    </ScreenContainer>
  );
}

const styles = StyleSheet.create({
  title: {
    fontSize: 24,
    marginBottom: 20,
    fontWeight: "bold",
  },
  info: {
    fontSize: 16,
    marginBottom: 10,
  },
});
