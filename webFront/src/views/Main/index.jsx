import React from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import { Main as MainLayout } from 'layouts';
import MainComponent from './components/MainComponent';
import SubComponent from './components/SubComponent';
import SubComponent2 from './components/SubComponent2';
import SubComponent3 from './components/SubComponent3';
import SubComponent4 from './components/SubComponent4';

const Main = () => {
  return (
    <MainLayout>
      <React.Fragment>
        <CssBaseline />
        <MainComponent />
        <SubComponent
          content="PickChat"
          content2="마음에 드는 회원을 'Pick' 해서 'Chat' 하세요."
        />
        <SubComponent2 />
        <SubComponent3 content="함께함의 가치를 믿습니다." />
        <SubComponent4 content="P I C K&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C H A T" />
      </React.Fragment>
    </MainLayout>
  );
};

export default Main;
