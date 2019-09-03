import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etf-execution-my-suffix.reducer';
import { IEtfExecutionMySuffix } from 'app/shared/model/etf-execution-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfExecutionMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EtfExecutionMySuffixDetail extends React.Component<IEtfExecutionMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { etfExecutionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EtfExecution [<b>{etfExecutionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="basketOrderId">Basket Order Id</span>
            </dt>
            <dd>{etfExecutionEntity.basketOrderId}</dd>
            <dt>
              <span id="stockCode">Stock Code</span>
            </dt>
            <dd>{etfExecutionEntity.stockCode}</dd>
            <dt>
              <span id="qty">Qty</span>
            </dt>
            <dd>{etfExecutionEntity.qty}</dd>
            <dt>
              <span id="qtyOdd">Qty Odd</span>
            </dt>
            <dd>{etfExecutionEntity.qtyOdd}</dd>
            <dt>
              <span id="price">Price</span>
            </dt>
            <dd>{etfExecutionEntity.price}</dd>
            <dt>
              <span id="amount">Amount</span>
            </dt>
            <dd>{etfExecutionEntity.amount}</dd>
          </dl>
          <Button tag={Link} to="/entity/etf-execution-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/etf-execution-my-suffix/${etfExecutionEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ etfExecution }: IRootState) => ({
  etfExecutionEntity: etfExecution.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfExecutionMySuffixDetail);
