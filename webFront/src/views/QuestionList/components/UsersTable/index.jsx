import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import Btn from '../createModal/Btn';

// Externals
import classNames from 'classnames';
import PropTypes from 'prop-types';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';

// Material helpers
import { withStyles, Button } from '@material-ui/core';

// Material components
import {
  Avatar,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
  TablePagination
} from '@material-ui/core';

import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';
import { Portlet, PortletContent } from 'components';
import { useDispatch } from 'react-redux';
// Component styles
import styles from './styles';
import Axios from 'axios';

const UsersTable = ({ classes, className, users }) => {
  const [state, setState] = React.useState({
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: []
  });

  const dispatch = useDispatch();

  React.useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/question/list').then(data =>
      setState({ client: data.data })
    );
  }, [state.client]);

  const rootClassName = classNames(classes.root, className);

  return (
    <Portlet className={rootClassName}>
      <PortletContent noPadding>
        <PerfectScrollbar>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  글번호
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  아이디
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  제목
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  내용
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  답글
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  등록일
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  답글등록
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem', fontWeight: 'bold'  }}>
                  삭제
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client.map(user => (
                    <TableRow className={classes.tableRow} hover key={user.qno}>
                      {/* <TableCell className={classes.tableCell}>
                        <div className={classes.tableCellInner}>
                          <Typography
                            className={classes.nameText}
                            variant="body1"
                            style={{ fontSize: '1rem' }}>
                            {user.qwriter}
                          </Typography>
                        </div>
                      </TableCell> */}
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.qno}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.qwriter}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.qtitle}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.qcontent}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.qccontent}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {moment(user.qregdate).format('DD/MM/YYYY')}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        <Button
                          onClick={() =>
                            dispatch({
                              type: 'Question/MODAL_CHECK',
                              payload: { no: user.qno }
                            })
                          }>
                          COMMENT
                        </Button>
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        <Button
                          onClick={() =>
                            dispatch({
                              type: 'Question/REMOVE',
                              payload: {
                                id: user.qno
                              }
                            })
                          }>
                          DELETE
                        </Button>
                      </TableCell>
                    </TableRow>
                  ))
                : ''}
            </TableBody>
          </Table>
        </PerfectScrollbar>
      </PortletContent>
      <Btn />
    </Portlet>
  );
};

export default withStyles(styles)(UsersTable);
