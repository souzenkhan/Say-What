import { Text, StyleSheet } from "react-native";
import ScreenContainer from "../components/ScreenContainer";
import PrimaryButton from "../components/PrimaryButton";
import { Audio } from "expo-av";
import { useRef, useEffect, useState } from "react";
import { useConnection } from "../context/ConnectionContext";

// Replace with your local IP address while testing
const BASE_URL = "http://YOUR_LOCAL_IP:3000";
const AUDIO_URL = `${BASE_URL}/audio`;

export default function AudioControlScreen() {
  const soundRef = useRef<Audio.Sound | null>(null);
  const { connectionState, setConnectionState } = useConnection();

  const [volume, setVolume] = useState(1);
  const [isMuted, setIsMuted] = useState(false);

  const handleConnect = async () => {
    try {
      setConnectionState("connecting");

      const response = await fetch(BASE_URL);

      if (response.ok) {
        setConnectionState("live");
      } else {
        setConnectionState("error");
      }
    } catch (e) {
      console.log("Connection error:", e);
      setConnectionState("error");
    }
  };

  const handleDisconnect = async () => {
    try {
      if (soundRef.current) {
        await soundRef.current.stopAsync();
        await soundRef.current.unloadAsync();
        soundRef.current = null;
      }

      setConnectionState("idle");
    } catch (e) {
      console.log("Disconnect error:", e);
      setConnectionState("error");
    }
  };

  const handlePlay = async () => {
    try {
      console.log("Trying to play...");
      console.log("Audio URL:", AUDIO_URL);

      setConnectionState("connecting");

      await Audio.setAudioModeAsync({
        playsInSilentModeIOS: true,
        staysActiveInBackground: false,
        shouldDuckAndroid: true,
      });

      if (soundRef.current) {
        await soundRef.current.playAsync();
        setConnectionState("live");
        console.log("Resuming audio");
        return;
      }

      const { sound } = await Audio.Sound.createAsync(
        { uri: AUDIO_URL },
        {
          shouldPlay: true,
          volume: isMuted ? 0 : volume,
          isMuted: isMuted,
        }
      );

      soundRef.current = sound;

      setConnectionState("live");
      console.log("Playing new audio");
    } catch (e) {
      console.log("Play error:", e);
      setConnectionState("error");
    }
  };

  const handlePause = async () => {
    try {
      if (soundRef.current) {
        await soundRef.current.pauseAsync();
        console.log("Paused audio");
      }
    } catch (e) {
      console.log("Pause error:", e);
      setConnectionState("error");
    }
  };

  const increaseVolume = async () => {
    const newVolume = Math.min(volume + 0.1, 1);
    setVolume(newVolume);

    if (soundRef.current) {
      await soundRef.current.setVolumeAsync(newVolume);
    }
  };

  const decreaseVolume = async () => {
    const newVolume = Math.max(volume - 0.1, 0);
    setVolume(newVolume);

    if (soundRef.current) {
      await soundRef.current.setVolumeAsync(newVolume);
    }
  };

  const toggleMute = async () => {
    const newMuted = !isMuted;
    setIsMuted(newMuted);

    if (soundRef.current) {
      await soundRef.current.setIsMutedAsync(newMuted);
    }
  };

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

      <Text style={styles.info}>Status: {connectionState}</Text>
      <Text style={styles.info}>Volume: {Math.round(volume * 100)}%</Text>
      <Text style={styles.info}>Muted: {isMuted ? "Yes" : "No"}</Text>

      <PrimaryButton title="Connect" onPress={handleConnect} />
      <PrimaryButton title="Disconnect" onPress={handleDisconnect} />
      <PrimaryButton title="Play" onPress={handlePlay} />
      <PrimaryButton title="Pause" onPress={handlePause} />
      <PrimaryButton title="Volume +" onPress={increaseVolume} />
      <PrimaryButton title="Volume -" onPress={decreaseVolume} />
      <PrimaryButton title={isMuted ? "Unmute" : "Mute"} onPress={toggleMute} />
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