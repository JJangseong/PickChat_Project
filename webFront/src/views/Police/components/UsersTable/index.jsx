import React from 'react';
import { Link } from 'react-router-dom';
import classNames from 'classnames';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { withStyles, Button } from '@material-ui/core';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography
} from '@material-ui/core';
import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';
import { Portlet, PortletContent } from 'components';
import styles from './styles';
import Axios from 'axios';
import { useDispatch } from 'react-redux';

const UsersTable = ({ classes, className }) => {
  const [state, setState] = React.useState({
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: []
  });

  const dispatch = useDispatch();

  React.useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/board/police/list').then(data =>
      setState({ client: data.data })
    );
  });

  const rootClassName = classNames(classes.root, className);
  return (
    <Portlet className={rootClassName}>
      <PortletContent noPadding>
        <PerfectScrollbar>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                  번호
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                  신고자
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                  신고 글
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                  신고 내용
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                  신고 유형
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                  삭제
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client.map(user => (
                    <TableRow
                      className={classes.tableRow}
                      hover
                      key={user.bpno}>
                      <TableCell className={classes.tableCell}>
                        <div className={classes.tableCellInner}>
                          <Link to="#">
                            <Typography
                              className={classes.nameText}
                              variant="body1"
                              style={{ fontSize: '1rem' }}>
                              {user.bpno}
                            </Typography>
                          </Link>
                        </div>
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.sender}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.bno}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.pcontent}
                      </TableCell>

                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.rpcontent}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        <Button
                          onClick={() =>
                            dispatch({
                              type: 'Police/DELETE',
                              payload: {
                                bpno: user.bpno
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
    </Portlet>
  );
};

export default withStyles(styles)(UsersTable);
