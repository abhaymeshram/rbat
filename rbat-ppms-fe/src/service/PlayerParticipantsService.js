import axios from 'axios'

// const API_BASE_URL = 'http://localhost:8080/player-participant-management/v1'

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/player-participant-management/v1';

class PlayerParticipantsService {

    retrieveAllPlayingParticipants() {
        return axios.get(`${API_BASE_URL}/player/getAll`);
    }

    addPlayer(player) {
        return axios.post(`${API_BASE_URL}/player/add`, player);
    }

    updatePlayingStatus(player, id) {
        return axios.put(`${API_BASE_URL}/player/update/${id}`, player);
    }

    deletePlayer(id) {
        return axios.delete(`${API_BASE_URL}/player/delete/${id}`);
    }
}

export default new PlayerParticipantsService()