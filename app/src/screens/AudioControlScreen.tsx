import { Text, StyleSheet } from "react-native";
import ScreenContainer from "../components/ScreenContainer";
import PrimaryButton from "../components/PrimaryButton";
import { Audio } from "expo-av";
import { useRef, useEffect, useState } from "react";
import { useConnection } from "../context/ConnectionContext";

// Replace with your local IP
const BASE_URL = "http://192.168.12.163:3000";

// LIVE STREAM URL
const AUDIO_URL = `${BASE_URL}/audio-live`;

export default function AudioControlScreen() {
  const soundRef = useRef<Audio.Sound | null>(null);

  const { connectionState, setConnectionState } = useConnection();

  const [volume, setVolume] = useState(1);
  const [isMuted, setIsMuted] = useState(false);

  // =========================
  // CONNECT
  // =========================
  const handleConnect = async () => {
    try {
      setConnectionState("connecting");

      const response = await fetch(BASE_URL);

      if (response.ok) {
        setConnectionState("ready");
      } else {
        setConnectionState("error");
      }
    } catch (e) {
      console.log("Connection error:", e);
      setConnectionState("error");
    }
  };

  // =========================
  // PLAY LIVE STREAM
  // =========================
  const handlePlay = async () => {
    try {
      console.log("Starting live stream...");
      console.log("URL:", AUDIO_URL);

      setConnectionState("connecting");

      await Audio.setAudioModeAsync({
        playsInSilentModeIOS: true,
        staysActiveInBackground: false,
        shouldDuckAndroid: true,
      });

      // Resume existing stream
      if (soundRef.current) {
        await soundRef.current.playAsync();

        setConnectionState("live");

        console.log("Resumed stream");

        return;
      }

      // Create new stream
      const { sound } = await Audio.Sound.createAsync(
        {
          uri: AUDIO_URL,
        },
        {
          shouldPlay: true,
          volume: volume,
          isMuted: isMuted,
        },
      );

      soundRef.current = sound;

      setConnectionState("live");

      console.log("Live stream playing");
    } catch (e) {
      console.log("Play error:", e);

      setConnectionState("error");
    }
  };

  // =========================
  // PAUSE
  // =========================
  const handlePause = async () => {
    try {
      if (soundRef.current) {
        await soundRef.current.pauseAsync();

        setConnectionState("paused");

        console.log("Paused");
      }
    } catch (e) {
      console.log("Pause error:", e);

      setConnectionState("error");
    }
  };

  // =========================
  // DISCONNECT
  // =========================
  const handleDisconnect = async () => {
    try {
      if (soundRef.current) {
        await soundRef.current.stopAsync();

        await soundRef.current.unloadAsync();

        soundRef.current = null;
      }

      setConnectionState("idle");

      console.log("Disconnected");
    } catch (e) {
      console.log("Disconnect error:", e);

      setConnectionState("error");
    }
  };

  // =========================
  // VOLUME
  // =========================
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

  // =========================
  // MUTE
  // =========================
  const toggleMute = async () => {
    const newMuted = !isMuted;

    setIsMuted(newMuted);

    if (soundRef.current) {
      await soundRef.current.setIsMutedAsync(newMuted);
    }
  };

  // =========================
  // CLEANUP
  // =========================
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

      <PrimaryButton title="Play Live Audio" onPress={handlePlay} />

      <PrimaryButton title="Pause" onPress={handlePause} />

      <PrimaryButton title="Disconnect" onPress={handleDisconnect} />

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
