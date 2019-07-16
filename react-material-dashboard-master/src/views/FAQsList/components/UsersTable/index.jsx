import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Faq from '../../../../data/Faq';
import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';

// Externals
import classNames from 'classnames';
import PropTypes from 'prop-types';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';

// Material helpers
import { withStyles } from '@material-ui/core';

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

// Shared helpers
import { getInitials } from 'helpers';

// Shared components
import { Portlet, PortletContent } from 'components';

// Component styles
import styles from './styles';
import Axios from 'axios';

class UsersTable extends Component {
  state = {
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: []
  };

  componentDidMount() {
    Axios.get('http://sungjin5891.cafe24.com/board/bbslist').then(data =>
      this.setState((this.client = data.data))
    );
  }

  handleSelectAll = event => {
    const { users, onSelect } = this.props;

    let selectedUsers;

    if (event.target.checked) {
      selectedUsers = users.map(user => user.userid);
    } else {
      selectedUsers = [];
    }

    this.setState({ selectedUsers });

    onSelect(selectedUsers);
  };

  handleSelectOne = (event, id) => {
    const { onSelect } = this.props;
    const { selectedUsers } = this.state;

    const selectedIndex = selectedUsers.indexOf(id);
    let newSelectedUsers = [];

    if (selectedIndex === -1) {
      newSelectedUsers = newSelectedUsers.concat(selectedUsers, id);
    } else if (selectedIndex === 0) {
      newSelectedUsers = newSelectedUsers.concat(selectedUsers.slice(1));
    } else if (selectedIndex === selectedUsers.length - 1) {
      newSelectedUsers = newSelectedUsers.concat(selectedUsers.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelectedUsers = newSelectedUsers.concat(
        selectedUsers.slice(0, selectedIndex),
        selectedUsers.slice(selectedIndex + 1)
      );
    }

    this.setState({ selectedUsers: newSelectedUsers });

    onSelect(newSelectedUsers);
  };

  handleChangePage = (event, page) => {
    this.setState({ page });
  };

  handleChangeRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value });
  };

  render() {
    const { classes, className, users } = this.props;
    const { activeTab, selectedUsers, rowsPerPage, page } = this.state;

    const rootClassName = classNames(classes.root, className);
    return (
      <Portlet className={rootClassName}>
        <PortletContent noPadding>
          <PerfectScrollbar>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell align="left">제목</TableCell>
                  <TableCell align="left">내용</TableCell>
                  <TableCell align="left">등록일</TableCell>
                  <TableCell align="left">삭제</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {Faq !== undefined
                  ? Faq.map(user => (
                      <TableRow
                        className={classes.tableRow}
                        hover
                        key={user.bno}
                        selected={selectedUsers.indexOf(user.bno) !== -1}>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem'}}>
                          {user.title}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem' }}>
                          {user.content}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem' }}>
                          {moment(user.regdate).format('DD/MM/YYYY')}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          <DeleteBtn id={user.nno} />
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
  }
}

UsersTable.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired,
  onSelect: PropTypes.func,
  onShowDetails: PropTypes.func,
  users: PropTypes.array.isRequired
};

UsersTable.defaultProps = {
  users: [],
  onSelect: () => {},
  onShowDetails: () => {}
};

export default withStyles(styles)(UsersTable);
