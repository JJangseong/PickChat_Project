import React from 'react';
import { Button } from '@material-ui/core';
import { useDispatch } from 'react-redux';

const DeleteBtn = React.memo(({ id }) => {
  const dispatch = useDispatch();
  return (
    <Button onClick={() => dispatch({ type: 'DELETE', id })}>Delete</Button>
  );
});

export default DeleteBtn;
