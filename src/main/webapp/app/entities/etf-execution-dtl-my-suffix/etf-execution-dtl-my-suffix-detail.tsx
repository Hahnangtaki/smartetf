import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etf-execution-dtl-my-suffix.reducer';
import { IEtfExecutionDtlMySuffix } from 'app/shared/model/etf-execution-dtl-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfExecutionDtlMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EtfExecutionDtlMySuffixDetail extends React.Component<IEtfExecutionDtlMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { etfExecutionDtlEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EtfExecutionDtl [<b>{etfExecutionDtlEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="basketOrderId">Basket Order Id</span>
            </dt>
            <dd>{etfExecutionDtlEntity.basketOrderId}</dd>
            <dt>
              <span id="transactionId">Transaction Id</span>
            </dt>
            <dd>{etfExecutionDtlEntity.transactionId}</dd>
            <dt>
              <span id="transactionType">Transaction Type</span>
            </dt>
            <dd>{etfExecutionDtlEntity.transactionType}</dd>
          </dl>
          <Button tag={Link} to="/entity/etf-execution-dtl-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/etf-execution-dtl-my-suffix/${etfExecutionDtlEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ etfExecutionDtl }: IRootState) => ({
  etfExecutionDtlEntity: etfExecutionDtl.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfExecutionDtlMySuffixDetail);
