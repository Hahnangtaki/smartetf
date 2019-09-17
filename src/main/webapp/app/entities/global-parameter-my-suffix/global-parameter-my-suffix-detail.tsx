import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './global-parameter-my-suffix.reducer';
import { IGlobalParameterMySuffix } from 'app/shared/model/global-parameter-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGlobalParameterMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class GlobalParameterMySuffixDetail extends React.Component<IGlobalParameterMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { globalParameterEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            GlobalParameter [<b>{globalParameterEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="prmId">Prm Id</span>
            </dt>
            <dd>{globalParameterEntity.prmId}</dd>
            <dt>
              <span id="prmName">Prm Name</span>
            </dt>
            <dd>{globalParameterEntity.prmName}</dd>
            <dt>
              <span id="prmType">Prm Type</span>
            </dt>
            <dd>{globalParameterEntity.prmType}</dd>
            <dt>
              <span id="appType">App Type</span>
            </dt>
            <dd>{globalParameterEntity.appType}</dd>
            <dt>
              <span id="intVal">Int Val</span>
            </dt>
            <dd>{globalParameterEntity.intVal}</dd>
            <dt>
              <span id="floatVal">Float Val</span>
            </dt>
            <dd>{globalParameterEntity.floatVal}</dd>
            <dt>
              <span id="strVal">Str Val</span>
            </dt>
            <dd>{globalParameterEntity.strVal}</dd>
            <dt>
              <span id="dateVal">Date Val</span>
            </dt>
            <dd>
              <TextFormat value={globalParameterEntity.dateVal} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="show">Show</span>
            </dt>
            <dd>{globalParameterEntity.show ? 'true' : 'false'}</dd>
            <dt>
              <span id="edit">Edit</span>
            </dt>
            <dd>{globalParameterEntity.edit ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/global-parameter-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/global-parameter-my-suffix/${globalParameterEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ globalParameter }: IRootState) => ({
  globalParameterEntity: globalParameter.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GlobalParameterMySuffixDetail);
