import React, { createContext, useContext, useState } from 'react'; //import React + hooks

type ConnectionState = 'idle' | 'connecting' | 'live' | 'error'; //allowed connection states

type ConnectionContextType = {
  connectionState: ConnectionState; // current connection state
  setConnectionState: React.Dispatch<React.SetStateAction<ConnectionState>>; //function to update state
};

const ConnectionContext = createContext<ConnectionContextType | undefined>(undefined); //create context (starts undefined)

export function ConnectionProvider({ children }: { children: React.ReactNode }) { //provider component
  const [connectionState, setConnectionState] = useState<ConnectionState>('idle'); //state + setter (default = idle)

  return (
    <ConnectionContext.Provider value={{ connectionState, setConnectionState }}> {/* pass state + setter to context */}
      {children} {/* render wrapped components */}
    </ConnectionContext.Provider>
  );
}

export function useConnection() { //custom hook to access context
  const context = useContext(ConnectionContext); //get context value

  if (!context) { //check if used outside provider
    throw new Error('useConnection must be used inside a ConnectionProvider'); //throw error if invalid usage
  }

  return context; // return connection state + setter
}
