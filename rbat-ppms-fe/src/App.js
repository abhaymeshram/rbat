import './App.css';
import PlayerParticipantsApp from './component/PlayerParticipantsApp';
import ListPlayersComponent from './component/ListPlayersComponent';

function App() {
  return (
     <div className="container">
      <PlayerParticipantsApp />
      <ListPlayersComponent/>
      </div>
  );
}

export default App;
