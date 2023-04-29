import React from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from 'react-bootstrap/Form';

const AddModal = (props) => {
    
    return(
        <Modal show={props.show} onHide={props.handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>{props.title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {props.message}
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Control
                        type="text"
                        placeholder={props.placeholder}
                        autoFocus
                        onChange={props.handleTextChange}
                        value={props.text}
                    />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="dark" onClick={props.handleSave}>Přidat</Button>
                <Button variant="dark" onClick={props.handleClose}>Zrušit</Button>
            </Modal.Footer>
        </Modal>
    )
};

export default AddModal;