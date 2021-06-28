import React, { Component } from 'react';
import PlayerParticipantsService from '../service/PlayerParticipantsService'
import { Modal, Button, Form } from 'react-bootstrap'

class ListPlayersComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            players: [],
            message: null,
            showAddNewPlayerModal: false,
            playerName: '',
            errorMessage: null
        }
        this.retrieveAllPlayingParticipants = this.retrieveAllPlayingParticipants.bind(this)
        this.deletePlayer = this.deletePlayer.bind(this)
        this.updatePlayingStatus = this.updatePlayingStatus.bind(this)
        this.handleTextChange = this.handleTextChange.bind(this);
        this.addPlayer = this.addPlayer.bind(this);
    }

    handleModal() {
        this.setState({ showAddNewPlayerModal: !this.state.showAddNewPlayerModal }) 
    }

    addPlayer() {
        console.log(this.state.playerName)
        if (this.state.playerName === '' || this.state.playerName.length === 0) {
            this.showErrorMessage(`Player can not be saved. Name is required.`);
            this.setState({ showAddNewPlayerModal: !this.state.showAddNewPlayerModal }) 
            return
        } 
        const player = { name: this.state.playerName };
        this.setState({ showAddNewPlayerModal: !this.state.showAddNewPlayerModal }) 
        PlayerParticipantsService.addPlayer(player)
         .then(
                response => {
                 this.showSuccessMessage(`Player added successfully.`);
                 this.retrieveAllPlayingParticipants();
             })
         .catch(error => { this.showErrorMessage(`Exception while adding player. Please try again later.`); })
        this.setState({playerName: ''});
    }
    
    handleTextChange(event) {
        this.setState({playerName: event.target.value});
    }
    
    componentDidMount() {
        this.retrieveAllPlayingParticipants();
        setInterval(this.retrieveAllPlayingParticipants, 5000);
    }

    retrieveAllPlayingParticipants() {
        PlayerParticipantsService.retrieveAllPlayingParticipants()
            .then(
                response => {
                    console.log(response);
                    this.setState({ players: response.data })
                })
    }

     deletePlayer(id) {
        PlayerParticipantsService.deletePlayer(id)
            .then(
                response => {
                    this.showSuccessMessage(`Player ${id} deleted successful`)
                    this.retrieveAllPlayingParticipants()
                })
            .catch(error => { this.showErrorMessage(`Exception while deleting player. Please try again later.`); })
     }
    
    updatePlayingStatus(id) {
        console.log('updating playing status ' + id)
        const updatePlayerReuquest = { message: 'I am playing' };
        PlayerParticipantsService.updatePlayingStatus(updatePlayerReuquest, id)
            .then(
                response => {
                    this.showSuccessMessage(`Updated Playing status`)
                    this.retrieveAllPlayingParticipants()
                })
            .catch(error => { this.showErrorMessage(`Exception while updating player. Please try again later.`); })
    }

    showSuccessMessage(message) {
    this.setState(prev => ({
        message: message
     }));

    this.change = setTimeout(() => {
      this.setState({message: null})
    }, 5000)
    }
    
    showErrorMessage(errorMessage) {
    this.setState(prev => ({
        errorMessage: errorMessage
     }));

    this.change = setTimeout(() => {
      this.setState({errorMessage: null})
    }, 5000)
  }

    render() {
        return (
            <div className="container">
                {this.state.message && <div class="alert btn-success">{this.state.message}</div>}
                {this.state.errorMessage && <div class="alert btn-warning">{this.state.errorMessage}</div>}
                <div class="btn_add_right">
                <button className="btn btn-success" onClick={() => this.handleModal()}>Add player</button>
                </div>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Player Id</th>
                                <th>Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                         <tbody>
                            {
                                this.state.players.map(
                                    player =>
                                        <tr key={player.id}>
                                            <td>{player.id}</td>
                                            <td>{player.name}</td>
                                            <td><button className="btn btn-success btn_margin_left" onClick={() => this.updatePlayingStatus(player.id)}>I am playing</button>
                                            <button className="btn btn-warning btn_margin_left" onClick={() => this.deletePlayer(player.id)}>Delete</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
        <div>  
        <Modal show={this.state.showAddNewPlayerModal} onHide={()=>this.handleModal()}>  
          <Modal.Header>Add New Player</Modal.Header>  
             <Modal.Body>
                <div class="container">
                    <Form.Group >
                         <Form.Control type="text"  onChange={this.handleTextChange} defaultValue={this.state.playerName} placeholder="Please enter player name" required/>           
                    </Form.Group>
                </div>
              </Modal.Body>  
          <Modal.Footer>  
            <Button class="btn btn-default" onClick={()=>this.handleModal()}>Close</Button>  
            <Button onClick={() => this.addPlayer(this.state.playerName)}>Save</Button>  
          </Modal.Footer>  
        </Modal>  
    </div>              
</div>           
)}
}

export default ListPlayersComponent